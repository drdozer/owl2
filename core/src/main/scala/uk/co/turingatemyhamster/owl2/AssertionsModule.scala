package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Assertions
 *
 * @author Matthew Pocock
 */
trait AssertionsModule {

  importedModules : owl2.AxiomsModule =>

  type SameIndividual <: Assertion

  type DifferentIndividuals <: Assertion

  type ClassAssertion <: Assertion

  type ObjectPropertyAssertion <: Assertion

  type NegativeObjectPropertyAssertion <: Assertion

  type DataPropertyAssertion <: Assertion

  type NegativeDataPropertyAssertion <: Assertion

}
