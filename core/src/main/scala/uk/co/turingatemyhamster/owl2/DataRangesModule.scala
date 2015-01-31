package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Data_Ranges
 *
 * @author Matthew Pocock
 */
trait DataRangesModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule =>

  type DataComplementOf <: DataRange

  type DataUnionOf <: DataRange

  type DataOneOf <: DataRange

  type DatatypeRestriction <: DataRange

  type DataIntersectionOf <: DataRange

  type FacetRestriction
}
