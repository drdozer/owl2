package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Object_Property_Axioms
 *
 * @author Matthew Pocock
 */
trait ObjectPropertyAxiomsModule {

  importedModules : owl2.AxiomsModule =>

  type EquivalentObjectProperties <: ObjectPropertyAxiom

  type DisjointObjectProperties <: ObjectPropertyAxiom

  type SubObjectPropertyOf <: ObjectPropertyAxiom
  
  type ObjectPropertyDomain <: ObjectPropertyAxiom

  type ObjectPropertyRange <: ObjectPropertyAxiom

  type InverseObjectProperties <: ObjectPropertyAxiom
}
