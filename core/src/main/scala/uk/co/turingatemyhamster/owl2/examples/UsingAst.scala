package uk.co.turingatemyhamster
package owl2
package examples

import ast._

import scala.language.implicitConversions

object UsingAst {

  implicit def stringToIRI(s: String): IRI = FullIRI(s)
  implicit def stringPairToIRI(ss: (String, String)): IRI = AbbreviatedIRI(prefixName = PrefixName(ss._1), abbreviatedString = ss._2)
  implicit def toClass[I](i: I)(implicit iIRI: I => IRI): Class = Class(iIRI(i))

  implicit class IRISyntax[I](val _i: I) extends AnyVal {
    def ⊑ (i: IRI)(implicit asIRI: I => IRI): SubClassOf = SubClassOf(Nil, _i, i)
  }
}

/**
 *
 *
 * @author Matthew Pocock
 */
class UsingAst {

  import UsingAst._

  Ontology(directlyImportsDocuments = "http://www.example.com/ontolgy1#"::Nil,
           ontologyAnnotations = Annotation(annotationProperty = AnnotationProperty("rdfs" -> "label"), annotationValue = StringLiteralNoLangauge("An example")) :: Nil,
           axioms = SubClassOf(subClassExpression = "a" -> "Child", superClassExpression = "a" -> "Person") :: Nil)

  Ontology(
    directlyImportsDocuments = FullIRI("http://www.example.com/2.0")::Nil,
    ontologyIRI = Some(FullIRI("http://www.example.com/importing-ontology")))

  Class(entityIRI = AbbreviatedIRI(prefixName = PrefixName("a"), abbreviatedString = "Child"))
  Class("a" -> "child")
  "a" -> "child" : Class

  SubClassOf(subClassExpression = "a" -> "Child", superClassExpression = "a" -> "Person")
  SubClassOf(Nil, "a" -> "Child", "a" -> "Person")

  ("a" -> "Child") ⊑ ("a" -> "Person")

  Declaration(Nil, Class("owl" -> "Thing"))
  Declaration(Nil, Class("owl" -> "Nothing"))
  Declaration(Nil, ObjectProperty("owl" -> "topObjectProperty"))
  Declaration(Nil, ObjectProperty("owl" -> "bottomObjectProperty"))
  Declaration(Nil, DataProperty("owl" -> "topDataProperty"))
  Declaration(Nil, DataProperty("owl" -> "bottomDataProperty"))
  Declaration(Nil, Datatype("rdfs" -> "Literal"))

}
