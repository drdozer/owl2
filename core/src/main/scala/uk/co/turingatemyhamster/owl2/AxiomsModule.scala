package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Axioms
 *
 * @author Matthew Pocock
 */
trait AxiomsModule {

  importedModules: owl2.OntologyModule =>

  type Declaration <: Axiom

  type ClassAxiom <: Axiom

  type ObjectPropertyAxiom <: Axiom

  type DataPropertyAxiom <: Axiom

  type DatatypeDefinition <: Axiom

  type HasKey <: Axiom

  type Assertion <: Axiom

  type AnnotationAxiom <: Axiom

}
