package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait DataRangesModuleImpl extends owl2.DataRangesModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule {
    type UnlimitedNatural <: BigInt
    type DataRange = ast.DataRange
    type Datatype = ast.Datatype
  } =>

  override final type DataComplementOf = ast.DataComplementOf

  override final type DataUnionOf = ast.DataUnionOf

  override final type DataOneOf = ast.DataOneOf

  override final type DatatypeRestriction = ast.DatatypeRestriction

  override final type DataIntersectionOf = ast.DataIntersectionOf

  override final type FacetRestriction = ast.FacetRestriction
}

case class DataComplementOf(arity: BigInt,
                            dataRange: DataRange) extends DataRange

// 2..* dataRanges
case class DataUnionOf(arity: BigInt, dataRanges: List[DataRange]) extends DataRange

case class DataOneOf(arity: BigInt, literals: List[Literal]) extends DataRange

case class DatatypeRestriction(arity: BigInt, datatype: Datatype) extends DataRange

// 2..* dataRanges
case class DataIntersectionOf(arity: BigInt, dataRanges: List[DataRange]) extends DataRange

case class FacetRestriction(constrainingFacet: IRI, restrictionValue: Literal)