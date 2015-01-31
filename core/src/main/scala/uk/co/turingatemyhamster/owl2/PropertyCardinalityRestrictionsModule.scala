package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Object_Property_Cardinality_Restrictions
 *
 * @author Matthew Pocock
 */
trait PropertyCardinalityRestrictionsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule =>

  type ObjectMaxCardinality <: ClassExpression

  type ObjectMinCardinality <: ClassExpression

  type ObjectExactCardinality <: ClassExpression

}
