package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Propositional_Connectives_and_Enumeration_of_Individuals
 *
 * @author Matthew Pocock
 */
trait PropositionalConnectivesAndEnumerationOfIndividualsModule {

  importedModules : owl2.IriModule with owl2.EntitiesLiteralsAnonymousIndividualsModule  =>

  type ObjectUnionOf <: ClassExpression

  type ObjectComplementOf <: ClassExpression

  type ObjectOneOf <: ClassExpression

  type ObjectIntersectionOf <: ClassExpression

}
