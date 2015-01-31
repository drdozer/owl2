package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Data_Property_Axioms
 *
 * @author Matthew Pocock
 */
trait DataPropertyAxiomsModule {

  importedModules : owl2.AxiomsModule =>

  type SubDataPropertyOf <: DataPropertyAxiom

  type DisjointDataProperties <: DataPropertyAxiom

  type EquivalentDataProperties <: DataPropertyAxiom

  type FunctionalDataProperty <: DataPropertyAxiom

  type DataPropertyDomain <: DataPropertyAxiom

  type DataPropertyRange <: DataPropertyAxiom

}
