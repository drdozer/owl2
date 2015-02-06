package uk.co.turingatemyhamster
package owl2
package examples

import ast._

import scala.language.implicitConversions

object UsingAst {

  implicit def stringToIRI(s: String): IRI = FullIRI(s)
  implicit def stringPairToIRI(ss: (String, String)): IRI = AbbreviatedIRI(prefixName = PrefixName(ss._1), abbreviatedString = ss._2)
  implicit def toClass[I](i: I)(implicit iIRI: I => IRI): Class = Class(iIRI(i))
  implicit def toDatatype[I](i: I)(implicit iIRI: I => IRI): Datatype = Datatype(i)
  implicit def toAnnotationProperty[I](i: I)(implicit iIRI: I => IRI): AnnotationProperty = AnnotationProperty(i)
  implicit def toNamedIndividual[I](i: I)(implicit iIRI: I => IRI): NamedIndividual = NamedIndividual(i)
  implicit def toDataProperty[I](i: I)(implicit iIRI: I => IRI): DataProperty = DataProperty(i)

  implicit class Syntax[I](val _i: I) extends AnyVal {
    def ⊑ [J](j: J) = examples.⊑(_i, j)
  }
}

case class ⊑ [Sub, Sup] (sub: Sub, sup: Sup)

object ⊑ {
  implicit def toSubClass[Sub, Sup](sc: ⊑[Sub, Sup])(implicit
                                                     ceSub: Sub => ClassExpression,
                                                     ceSup: Sup => ClassExpression): SubClassOf =
    SubClassOf(Nil, sc.sub, sc.sup)
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

  val aPerson1 = "a" -> "person" : Class

  SubClassOf(
    subClassExpression = Class(AbbreviatedIRI(PrefixName("a"), "Child")),
    superClassExpression = Class(AbbreviatedIRI(PrefixName("a"), "Person")))

  SubClassOf(
    subClassExpression = "a" -> "Child",
    superClassExpression = "a" -> "Person")

  SubClassOf(Nil, "a" -> "Child", "a" -> "Person")

  val personSubsumesChild1 = ⊑(aChild1, aPerson1)
  val personSubsumesChild2 = ⊑(aChild1, aPerson1) : SubClassOf
  val personSubsumesChild3 = aChild1 ⊑ aPerson1
  val personSubsumesChild4 = aChild1 ⊑ aPerson1 : SubClassOf

  personSubsumesChild1
  personSubsumesChild2
  personSubsumesChild3
  personSubsumesChild4

  ("a" -> "Child") ⊑ ("a" -> "Person") : SubClassOf
  ("a" -> "Child") ⊑ ("a" -> "Person") : ClassAxiom

  val xsdInteger1 = "xsd" -> "integer"
  val xsdInteger2 = "xsd" -> "integer" : Datatype
  val xsdInteter3 = "xsd" -> "integer" : DataRange

  DataPropertyRange(Nil, "a" -> "hasAge", "xsd" -> "integer")

  Declaration(Nil, Class("owl" -> "Thing"))
  Declaration(Nil, Class("owl" -> "Nothing"))
  Declaration(Nil, ObjectProperty("owl" -> "topObjectProperty"))
  Declaration(Nil, ObjectProperty("owl" -> "bottomObjectProperty"))
  Declaration(Nil, DataProperty("owl" -> "topDataProperty"))
  Declaration(Nil, DataProperty("owl" -> "bottomDataProperty"))
  Declaration(Nil, Datatype("rdfs" -> "Literal"))
}
