package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Data_Property_Restrictions
 *
 * @author Matthew Pocock
 */
trait DataPropertyRestrictionsModule {

  importedModules : owl2.IriModule with owl2.EntitiesLiteralsAnonymousIndividualsModule =>

  type DataSomeValuesFrom <: ClassExpression

  type DataAllValuesFrom <: ClassExpression

  type DataHasValue <: ClassExpression

}
