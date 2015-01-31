package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Data_Property_Cardinality_Restrictions
 *
 * @author Matthew Pocock
 */
trait DataPropertyCardinalityRestrictionsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule =>

  type DataMinCardinality <: ClassExpression

  type DataMaxCardinality <: ClassExpression

  type DataExactCardinality <: ClassExpression

}
