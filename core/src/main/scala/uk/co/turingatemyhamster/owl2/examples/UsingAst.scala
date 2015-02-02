package uk.co.turingatemyhamster
package owl2
package examples

import ast._

/**
 *
 *
 * @author Matthew Pocock
 */
class UsingAst {

  implicit def stringToIRI(s: String): IRI = FullIRI(s)
  implicit def stringPairToIRI(ss: (String, String)): IRI = AbbreviatedIRI(prefixName = PrefixName(ss._1), abbreviatedString = ss._2)
  implicit def toClass[I](i: I)(implicit iIRI: I => IRI): Class = Class(iIRI(i))


  Ontology(
    directlyImportsDocuments = FullIRI("http://www.example.com/2.0")::Nil,
    ontologyIRI = Some(FullIRI("http://www.example.com/importing-ontology")))

  Class(entityIRI = AbbreviatedIRI(prefixName = PrefixName("a"), abbreviatedString = "Child"))
  Class("a" -> "child")
  "a" -> "child" : Class

  SubClassOf(subClassExpression = "a" -> "Child", superClassExpression = "a" -> "Person")

}
