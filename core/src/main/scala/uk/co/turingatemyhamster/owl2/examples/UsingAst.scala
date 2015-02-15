package uk.co.turingatemyhamster
package owl2
package examples

import ast._

import scala.language.implicitConversions

object UsingAst {

  implicit class W3CHelper(val sc: StringContext) extends AnyVal {
    //    def pn(args: Any*): PrefixName = ???
    //    def iri(args: Any*): IRI = ???
    def pl(args: Any*): AbbreviatedIRI = {
      val s = sc.s(args :_*)
      val i = s.indexOf(":")
      val (pn, ln) = s.splitAt(i+1)
      AbbreviatedIRI(PrefixName(pn), ln)
    }

    def lit(args: Any*): StringLiteralNoLangauge =
      StringLiteralNoLangauge(sc.s(args :_*))
  }

  implicit def stringPairToIRI(ss: (String, String)): IRI = AbbreviatedIRI(prefixName = PrefixName(ss._1), abbreviatedString = ss._2)
  implicit def toClass[I](i: I)(implicit iIRI: I => IRI): Class = Class(iIRI(i))
  implicit def toDatatype[I](i: I)(implicit iIRI: I => IRI): Datatype = Datatype(i)
  implicit def toAnnotationProperty[I](i: I)(implicit iIRI: I => IRI): AnnotationProperty = AnnotationProperty(i)
  implicit def toNamedIndividual[I](i: I)(implicit iIRI: I => IRI): NamedIndividual = NamedIndividual(i)
  implicit def toObjectProperty[I](i: I)(implicit iIRI: I => IRI): ObjectProperty = ObjectProperty(i)
  implicit def toDataProperty[I](i: I)(implicit iIRI: I => IRI): DataProperty = DataProperty(i)

  implicit class Syntax[I](val _i: I) extends AnyVal {
    def ⊑ [J](j: J) = examples.⊑(_i, j)

    def is_a [J](j: J)(implicit iIndividual: I => Individual, jIRI: J => ClassExpression): ClassAssertion =
      ClassAssertion(Nil, _i, j)

    def --- [J](j: J) = new {
      def --> [K](k: K) = Triple(_i, j, k)
    }

    def inverse = Inverse(_i)

    def complement = Complement(_i)

    def ≤ (c: Int): ≤[I] = examples.≤(_i, BigInt(c))

    def === (c: Int): ===[I] = examples.===(_i, BigInt(c))

    def ≥ (c: Int): ≥[I] = examples.≥(_i, BigInt(c))

    def ^^[J](j: J)(implicit iString: I => String, jDatatype: J => Datatype): TypedLiteral = TypedLiteral(_i, j)

    def | [J](j: J)(implicit iDT: I => Datatype, jFR: J => FacetRestriction): DatatypeRestriction =
      DatatypeRestriction(datatype = _i, restrictions = j::Nil)

    def --> [J](j: J)(implicit iP: I => AnnotationProperty, jV: J => AnnotationValue) : Annotation =
      Annotation(Nil, _i, j)

    def ## (anns: Annotation*): examples.##[I] = examples.##(_i, anns.to[List])

    def =⨃[J](j1: J, j2: J, js: J*): ⨃[J, I] = ⨃(_i, j1, j2, js.to[List])

    def ≡[J](j: J): ≡≡[I,J] = ≡≡(_i, j)

    def ⊓[J](j: J): ⊓⊓[I, J] = ⊓⊓(_i, j)

    def ¬ = _i.complement
  }
}

case class ##[A](ax: A, anns: List[Annotation])

object ## {
  implicit def toAxiom[A, AX <: Axiom](annotated: ##[A])(implicit aAx: A => AX): AX = {
    val ax = annotated.ax : AX
    ax.copy(axiomAnnotations = ax.axiomAnnotations ++ annotated.anns)
  }
}

case class ⊑ [Sub, Sup] (sub: Sub, sup: Sup)

object ⊑ {
  implicit def toSubClass[Sub, Sup](sc: ⊑[Sub, Sup])(implicit
                                                     ceSub: Sub => ClassExpression,
                                                     ceSup: Sup => ClassExpression): SubClassOf =
    SubClassOf(Nil, sc.sub, sc.sup)

  implicit def toSubObjectPropertyOf[Sub, Sup](sc: ⊑[Sub, Sup])(implicit
                                                                opeSub: Sub => ObjectPropertyExpression,
                                                                opeSup: Sup => ObjectPropertyExpression): SubObjectPropertyOf =
    SubObjectPropertyOf(Nil, sc.sub, (sc.sup : ObjectPropertyExpression) :: Nil)
}

case class Triple[Sub, Pred, Obj](sub: Sub, pred: Pred, obj: Obj)

object Triple {
  implicit def toAnnotation[Sub, Pred, Obj](t: Triple[Sub, Pred, Obj]
                                             )(implicit
                                               subSub: Sub => AnnotationSubject,
                                               predProp: Pred => AnnotationProperty,
                                               objVal: Obj => AnnotationValue): AnnotationAssertion =
    AnnotationAssertion(Nil, t.sub, t.pred, t.obj)

  implicit def toObjectPropertyAssertion[Sub, Pred, Obj](t: Triple[Sub, Pred, Obj]
                                                          )(implicit
                                                            subIndividual: Sub => Individual,
                                                            predPE: Pred => ObjectPropertyExpression,
                                                            objIndividual: Obj => Individual): ObjectPropertyAssertion =
    ObjectPropertyAssertion(Nil, t.sub, t.pred, t.obj)

  implicit def toDataPropertyAssertion[Sub, Pred, Obj](t: Triple[Sub, Pred, Obj]
                                                        )(implicit
                                                          subIndividual: Sub => Individual,
                                                          predPE: Pred => DataPropertyExpression,
                                                          objLit: Obj => Literal): DataPropertyAssertion =
    DataPropertyAssertion(Nil, t.sub, t.pred, t.obj)

}

case class Inverse[P](p: P) {
  def inverse: P = p
}

object Inverse {
  implicit def inverseOfP[P](i: Inverse[P])(implicit pP : P => ObjectProperty): InverseObjectProperty =
    InverseObjectProperty(i.p)
  implicit def inverseOfI(i: Inverse[InverseObjectProperty]): ObjectProperty =
    i.p.objectProperty
}

case class Complement[T](t: T) {
  def complement: T = t
}

object Complement {
  implicit def dataComplement[T](c: Complement[T])(implicit tD: T => DataRange): DataComplementOf =
    DataComplementOf(dataRange = c.t)

  implicit def objectComplement[T](c: Complement[T])(implicit cC: T => ClassExpression): ObjectComplementOf =
    ObjectComplementOf(classExpression = c.t)
}

case class ⊓ [I](i1: I, i2: I, is: List[I])

object ⊓ {
  def apply[I](i1: I, i2: I, is: I*): ⊓[I] = ⊓(i1, i2, is.to[List])

  implicit def objectIntersection[I](oi: ⊓[I])(implicit iCE: I => ClassExpression): ObjectIntersectionOf =
    ObjectIntersectionOf(oi.i1 :: oi.i2 :: oi.is map iCE)
  
  implicit def dataIntersection[I](oi: ⊓[I])(implicit iDR: I => DataRange): DataIntersectionOf =
      DataIntersectionOf(dataRanges = oi.i1 :: oi.i2 :: oi.is map iDR)
}

case class ⊓⊓[I, J](i: I, j: J)

object ⊓⊓ {
  implicit def objectIntersection[I, J](oi: ⊓⊓[I, J])(implicit
                                                      iCE: I => ClassExpression,
                                                      jCE: J => ClassExpression): ObjectIntersectionOf =
    ObjectIntersectionOf(iCE(oi.i)::jCE(oi.j)::Nil)
}

case class ⊔ [I](i1: I, i2: I, is: List[I])

object ⊔ {
  def apply[I](i1: I, i2: I, is: I*): ⊔[I] = ⊔(i1, i2, is.to[List])

  implicit def objectUnion[I](oi: ⊔ [I])(implicit iCE: I => ClassExpression): ObjectUnionOf =
    ObjectUnionOf(oi.i1 :: oi.i2 :: oi.is map iCE)

  implicit def dataUnion[I](oi: ⊔[I])(implicit iDR: I => DataRange): DataUnionOf =
      DataUnionOf(dataRanges = oi.i1 :: oi.i2 :: oi.is map iDR)
}

case class ⊔=∅[I](i1: I, i2: I, is: List[I])

case object ⊔=∅ {
  def apply[I](i1: I, i2: I, is: I*): ⊔=∅[I] = ⊔=∅(i1, i2, is.to[List])

  implicit def toDisjointClasses[I](disj: ⊔=∅[I])(implicit iCE: I => ClassExpression): DisjointClasses =
    DisjointClasses(Nil, (disj.i1 :: disj.i2 :: disj.is) map iCE)

  implicit def toDisjointObjectProperties[I](disj: ⊔=∅[I])(implicit iP: I => ObjectPropertyExpression): DisjointObjectProperties =
    DisjointObjectProperties(Nil, (disj.i1 :: disj.i2 :: disj.is) map iP)

  implicit def toDisjointDataProperties[I](disj: ⊔=∅[I])(implicit iP: I => DataPropertyExpression): DisjointDataProperties =
    DisjointDataProperties(Nil, (disj.i1 :: disj.i2 :: disj.is) map iP)
}

case class ⨃[I, C](c: C, i1: I, i2: I, is: List[I])

object ⨃ {
  def apply[I, C](c: C, i1: I, i2: I, is: I*): ⨃[I, C] = ⨃(c: C, i1, i2, is.to[List])

  def toDisjointUnion[I, C](disj: ⨃[I, C])(implicit iCE: I => ClassExpression, cC: C => Class): DisjointUnion =
      DisjointUnion(Nil, (disj.i1 :: disj.i2 :: disj.is) map iCE, disj.c)
}

case class ≡[I](i1: I, i2: I, is: List[I])

object ≡ {
  def apply[I](i1: I, i2: I, is: I*): ≡ [I] = ≡(i1, i2, is.to[List])

  implicit def toEquivalentClasses[I](equiv: ≡[I])(implicit iClassExpression: I => ClassExpression): EquivalentClasses =
    EquivalentClasses(Nil, (equiv.i1 :: equiv.i2 :: equiv.is) map iClassExpression)

  implicit def toEquivalentObjectProperties[I](equiv: ≡[I])(implicit iP: I => ObjectPropertyExpression): EquivalentObjectProperties =
    EquivalentObjectProperties(Nil, (equiv.i1 :: equiv.i2 :: equiv.is) map iP)

  implicit def toEquivalentDataProperties[I](equiv: ≡[I])(implicit iP: I => DataPropertyExpression): EquivalentDataProperties =
    EquivalentDataProperties(Nil, (equiv.i1 :: equiv.i2 :: equiv.is) map iP)
}

case class ≡≡[I, J](i: I, j: J)

object ≡≡ {
  implicit def toEquivalentClasses[I, J](equiv: ≡≡[I, J])(implicit
                                                          iCE: I => ClassExpression,
                                                          jCE: J => ClassExpression): EquivalentClasses =
    EquivalentClasses(Nil, iCE(equiv.i)::jCE(equiv.j)::Nil)
}

case class ≢[I](i1: I, i2: I, is: List[I])

object ≢ {
  def apply[I](i1: I, i2: I, is: I*): ≢[I] = ≢(i1, i2, is.to[List])

  implicit def toDifferentIndividuals[I](nequiv: ≢[I])(implicit iIndividual: I => Individual): DifferentIndividuals =
    DifferentIndividuals(Nil, (nequiv.i1 :: nequiv.i2 :: nequiv.is) map iIndividual)
}

case class ∋[I](i1: I, is: List[I])

object ∋ {
  def apply[I](i1: I, is: I*): ∋[I] = ∋(i1, is.to[List])

  implicit def toObjectOneOf[I](members: ∋[I])(implicit iIndividual: I => Individual): ObjectOneOf =
    ObjectOneOf((members.i1 :: members.is) map iIndividual)
}

case class ∃[P, V](p: P, v: V)

case object ∃ {
  implicit def someObjectSomeValuesFrom[P, V](some: ∃[P, V])(implicit
                                                             pPE: P => ObjectPropertyExpression,
                                                             vCE: V => ClassExpression): ObjectSomeValuesFrom =
    ObjectSomeValuesFrom(some.p, some.v)

  implicit def someDataSomeValuesFrom[P, V](some: ∃[P, V])(implicit
                                                           pPE: P => DataPropertyExpression,
                                                           vDR: V => DataRange): DataSomeValuesFrom =
    DataSomeValuesFrom(some.p::Nil, some.v)
}

case class ∀[P, V](p: P, v: V)

case object ∀ {
  implicit def allObjectValuesFrom[P, V](all: ∀[P, V])(implicit
                                                       pPE: P => ObjectPropertyExpression,
                                                       vCE: V => ClassExpression): ObjectAllValuesFrom =
    ObjectAllValuesFrom(all.p, all.v)

  implicit def allDataValuesFrom[P, V](all: ∀[P, V])(implicit
                                                     pPE: P => DataPropertyExpression,
                                                     vDR: V => DataRange): DataAllValuesFrom =
    DataAllValuesFrom(all.p::Nil, all.v)
}

case class ∈[P, V](p: P, v: V)

object ∈ {
  implicit def toObjectHasValue[P, V](pv: ∈[P, V])(implicit
                                                   pPE: P => ObjectPropertyExpression,
                                                   vI: V => Individual): ObjectHasValue =
    ObjectHasValue(pv.p, pv.v)

  implicit def toDataHasValue[P, V](pv: ∈[P, V])(implicit
                                                 pPE: P => DataPropertyExpression,
                                                 vI: V => Literal): DataHasValue =
    DataHasValue(pv.p, pv.v)

  implicit def toFacetRestriction[P, V](pv: ∈[P, V])(implicit
                                                     pI: P => IRI,
                                                     vI: V => Literal): FacetRestriction =
    FacetRestriction(pv.p, pv.v)
}

case class ≤[P](p: P, c: BigInt)

object ≤ {
  implicit def toObjectMaxCardinality[P](lteq: ≤[P])(implicit pPE: P => ObjectPropertyExpression): ObjectMaxCardinality =
    ObjectMaxCardinality(lteq.p, None, lteq.c)

  implicit def toDataMinCardinality[P](lteq: ≤[P])(implicit pPE: P => DataPropertyExpression): DataMaxCardinality =
      DataMaxCardinality(lteq.p, None, lteq.c)
}

case class ===[P](p: P, c: BigInt)

object === {
  implicit def toObjectExactCardinality[P](eq: ===[P])(implicit pPE: P => ObjectPropertyExpression): ObjectExactCardinality =
    ObjectExactCardinality(eq.p, None, eq.c)

  implicit def toDataExactCardinality[P](eq: ===[P])(implicit pPE: P => DataPropertyExpression): DataExactCardinality =
    DataExactCardinality(eq.p, None, eq.c)
}

case class ≥[P](p: P, c: BigInt)

object ≥ {
  implicit def toObjectMaxCardinality[P](gteq: ≥[P])(implicit pPE: P => ObjectPropertyExpression): ObjectMinCardinality =
    ObjectMinCardinality(gteq.p, None, gteq.c)

  implicit def toDataMaxCardinality[P](gteq: ≥[P])(implicit pPE: P => DataPropertyExpression): DataMinCardinality =
    DataMinCardinality(gteq.p, None, gteq.c)
}


/**
 *
 *
 * @author Matthew Pocock
 */
class UsingAst {

  import UsingAst._

  Ontology(
    directlyImportsDocuments = "http://www.example.com/ontolgy1#"::Nil,
    ontologyAnnotations = Annotation(annotationProperty = AnnotationProperty("rdfs" -> "label"),
      annotationValue = StringLiteralNoLangauge("An example")) :: Nil,
    axioms = SubClassOf(subClassExpression = "a" -> "Child", superClassExpression = "a" -> "Person") :: Nil)

  Ontology(
    directlyImportsDocuments = FullIRI("http://www.example.com/2.0")::Nil,
    ontologyIRI = Some(FullIRI("http://www.example.com/importing-ontology")))

  val aChild1 = Class(entityIRI = AbbreviatedIRI(prefixName = PrefixName("a"), abbreviatedString = "Child"))
  val aChild2 = Class("a" -> "child")
  val aChild3 = "a" -> "child" : Class
  val aChild4 = pl"a:child" : Class

  val aPerson1 = pl"a:person" : Class

  SubClassOf(
    subClassExpression = Class(AbbreviatedIRI(PrefixName("a"), "Child")),
    superClassExpression = Class(AbbreviatedIRI(PrefixName("a"), "Person")))

  SubClassOf(
    subClassExpression = "a" -> "Child",
    superClassExpression = "a" -> "Person")

  SubClassOf(Nil, "a" -> "Child", "a" -> "Person")

  ⊑(aChild1, aPerson1)
  ⊑(aChild1, aPerson1) : SubClassOf
  aChild1 ⊑ aPerson1
  aChild1 ⊑ aPerson1 : SubClassOf

  pl"a:Child" ⊑ pl"a:Person" : SubClassOf
  pl"a:Child" ⊑ pl"a:Person" : ClassAxiom

  val xsdInteger1 = pl"xsd:integer"
  val xsdInteger2 = pl"xsd:integer" : Datatype
  val xsdInteter3 = pl"xsd:integer" : DataRange

  DataPropertyRange(Nil, pl"a:hasAge", pl"xsd:integer")

  Declaration(Nil, Class("owl" -> "Thing"))
  Declaration(Nil, Class("owl" -> "Nothing"))
  Declaration(Nil, ObjectProperty("owl" -> "topObjectProperty"))
  Declaration(Nil, ObjectProperty("owl" -> "bottomObjectProperty"))
  Declaration(Nil, DataProperty("owl" -> "topDataProperty"))
  Declaration(Nil, DataProperty("owl" -> "bottomDataProperty"))
  Declaration(Nil, Datatype("rdfs" -> "Literal"))

  ClassAssertion(Nil, "a" -> "Dog", "a" -> "Brian")
  ClassAssertion(Nil, "a" -> "Species", "a" -> "Dog")
  ClassAssertion(Nil, "a" -> "PetAnimal", "a" -> "Dog")

  pl"a:Brian" is_a pl"a:Dog"
  pl"a:Dog" is_a pl"a:species"
  pl"a:Dog" is_a pl"a:PetAnimal"
  
  AnnotationAssertion(Nil, "a" -> "Dog", "a" -> "addedBy", lit"Seth MacFarlane")
  pl"a:Dog" --- pl"a:addedBy" --> lit"Seth MacFarlane" : AnnotationAssertion

  ObjectPropertyAssertion(Nil, "a" -> "Peter", "a" -> "fatherOf", "a" -> "Stewie")
  pl"a:Peter" --- pl"a:fatherOf" --> pl"a:Stewie" : ObjectPropertyAssertion

  InverseObjectProperty("a" -> "fatherOf")
  pl"a:fatherOf" : ObjectProperty
  pl"a:fatherOf".inverse : InverseObjectProperty
  (pl"a:fatherOf".inverse : InverseObjectProperty).inverse : ObjectProperty
  pl"a:fatherOf".inverse.inverse : ObjectProperty

  DataIntersectionOf(dataRanges =
    ("xsd" -> "nonNegativeInteger" : DataRange) ::
      ("xsd" -> "nonPositiveInteger" : DataRange) ::
      Nil)
  ⊓(pl"xsd:nonNegativeInteger", pl"xsd:nonPositiveInteger") : DataIntersectionOf

  DataUnionOf(dataRanges =
    ("xsd" -> "string" : DataRange) ::
      ("xsd" -> "integer" : DataRange) ::
      Nil)
  ⊔(pl"xsd:string", pl"xsd:integer") : DataUnionOf

  DataComplementOf(dataRange = "xsd" -> "positiveInteger")
  pl"xsd:positiveInteger".complement : DataComplementOf
  pl"xsd:positiveInteger".complement.complement : Datatype

  ObjectIntersectionOf(("a" -> "Dog" : ClassExpression) :: ("a" -> "CanTalk" : ClassExpression) :: Nil)
  ⊓(pl"a:Dog", pl"a:CanTalk") : ObjectIntersectionOf

  ObjectUnionOf(("a" -> "Man" : ClassExpression) :: ("a" -> "Woman" : ClassExpression) :: Nil)
  ⊔("a" -> "Man", "a" -> "Woman") : ObjectUnionOf

  ObjectComplementOf("a" -> "man")
  pl"a:man".complement : ObjectComplementOf
  pl"a:man".complement.complement : ClassExpression

  EquivalentClasses(Nil, List("a" -> "GriffinFamilyMember" : ClassExpression, ObjectOneOf(
    List("a" -> "Peter" : Individual,
    "a" -> "Lois",
    "a" -> "Stewie",
    "a" -> "Meg",
    "a" -> "Chris",
    "a" -> "Brian")
  )))
  DifferentIndividuals(individuals = List("a" -> "Quagmire" : Individual,
    "a" -> "Peter",
    "a" -> "Lois",
    "a" -> "Stewie",
    "a" -> "Meg",
    "a" -> "Chris",
    "a" -> "Brian")
  )
  pl"a:GriffinFamilyMember".complement : ClassExpression

  ≡(pl"a:GriffinFamilyMember" : ClassExpression,
    ∋(
      pl"a:Peter",
      pl"a:Lois",
      pl"a:Stewie",
      pl"a:Meg",
      pl"a:Chris",
      pl"a:Brian") : ClassExpression) : ClassAxiom

  ≢(pl"a:Quagmire",
    pl"a:Peter",
    pl"a:Lois",
    pl"a:Stewie",
    pl"a:Meg",
    pl"a:Chris",
    pl"a:Brian") : DifferentIndividuals


  ObjectPropertyAssertion(Nil, "a" -> "Peter", "a" -> "fatherOf", "a" -> "Stewie")
  ClassAssertion(Nil, "a" -> "Stewie", "a" -> "Man")

  pl"a:Peter" --- pl"a:fatherOf" --> pl"a:Stewie"
  pl"a:Stewie" is_a pl"a:Man"

  ObjectSomeValuesFrom("a" -> "fatherOf", "a" -> "Man")
  ∃(pl"a:fatherOf", pl"a:Man") : ObjectSomeValuesFrom

  ObjectAllValuesFrom("a" -> "hasPet", "a" -> "Dog")
  ∀(pl"a:hasPet", pl"a:Dog") : ObjectAllValuesFrom

  ObjectHasValue("a" -> "hasPet", "a" -> "Brian")
  ∈(pl"a:hasPet", pl"a:Brian") : ObjectHasValue

  ObjectHasSelf("a" -> "likes")

  ObjectMaxCardinality(objectPropertyExpression = "a" -> "hasPet", cardinality = BigInt(1))
  pl"a:Peter" is_a (pl"a:hasPet" ≤ 1 : DataMaxCardinality)

  ObjectExactCardinality(objectPropertyExpression = "a" -> "hasPet", cardinality = BigInt(1))
  pl"a:hasPet" === 1 : ObjectExactCardinality

  ObjectMinCardinality(objectPropertyExpression = "a" -> "hasPet", cardinality = BigInt(1))
  pl"a:hasPet" ≥ 1 : ObjectMinCardinality

  DataPropertyAssertion(Nil, "a" -> "Meg", "a" -> "hasAge", "17"^^("xsd" -> "integer"))
  pl"a:Meg" --- pl"a:hasAge" --> ("17"^^pl"xsd:integer") : DataPropertyAssertion

  DataSomeValuesFrom(
    ("a" -> "hasAge")::Nil,
    DatatypeRestriction(
      BigInt(1),
      "xsd" -> "integer",
      FacetRestriction("xsd" -> "maxExclusive", "20"^^("xsd" -> "integer"))::Nil),
    BigInt(1))

  ∃(pl"a:hasAge", pl"xsd:integer" | ∈(pl"xsd:maxExclusive", "20"^^pl"xsd:integer")) : DataSomeValuesFrom
  ∀(pl"a:hasZIP", pl"xsd:integer") : DataAllValuesFrom
  ∈(pl"a:hasAge", "17"^^pl"xsd:integer") : DataHasValue

  DataMinCardinality(pl"a:hasName", None, BigInt(2))
  pl"a:hasName" ≥ 2 : DataMinCardinality
  pl"a:hasName" === 2 : DataExactCardinality
  pl"a:hasName" ≤ 2 : DataMaxCardinality

  SubClassOf(Annotation(Nil, pl"rdfs:comment", lit"Male people are people.")::Nil, pl"a:Man", pl"a:Person")
  (pl"a:Man" ⊑ pl"Person" : SubClassOf) ## (pl"rdfs:comment" --> lit"Male people are people")
  (pl"a:Man" ⊑ pl"Person" : ClassAxiom) ## (pl"rdfs:comment" --> lit"Male people are people")

  examples.##.toAxiom((pl"a:Man" ⊑ pl"Person") ## (pl"rdfs:comment" --> lit"Male people are people")) : ClassAxiom
  (pl"a:Man" ⊑ pl"Person") ## (pl"rdfs:comment" --> lit"Male people are people") : ClassAxiom

  SubObjectPropertyOf(Nil, "a" -> "hasDog", ("a" -> "hasPet" : ObjectPropertyExpression) :: Nil)
  ObjectPropertyAssertion(Nil, "a" -> "Peter", "a" -> "hasDog", "a" -> "Brian")
  ObjectPropertyAssertion(Nil, "a" -> "Peter", "a" -> "hasPet", "a" -> "Brian")

  pl"a:hasDog" ⊑ pl"a:hasPet" : SubObjectPropertyOf
  pl"a:Peter" --- pl"a:hasDog" --> pl"a:Brian"
  pl"a:Peter" --- pl"a:hasPet" --> pl"a:Brian"

  pl"a:Peter" --- (pl"a:hasDog" --> pl"a:Brian",
                   pl"a:hasPet" --> pl"a:Brian")

  pl"a:Meg" --- pl"a:hasName" --> (lit"Meg",
                                   lit"Meggan")


  pl"a:ChildlessPerson" ≡ (pl"a:Person" ⊓ pl"a:Parent"¬)
}
