package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Class_Expression_Axioms
 *
 * @author Matthew Pocock
 */
trait ClassExpressionAxiomsModule {

  importedModules : owl2.AxiomsModule =>

  type SubClassOf <: ClassAxiom

  type EquivalentClasses <: ClassAxiom

  type DisjointClasses <: ClassAxiom

  type DisjointUnion <: ClassAxiom

}
