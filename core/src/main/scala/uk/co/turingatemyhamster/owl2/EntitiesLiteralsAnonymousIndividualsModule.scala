package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Entities.2C_Literals.2C_and_Anonymous_Individuals
 *
 * @author Matthew Pocock
 */
trait EntitiesLiteralsAnonymousIndividualsModule {

  importedModules : owl2.IriModule =>

  type ClassExpression <: AnyRef

  type Entity <: AnyRef

  // note: DataRange is refined in DataRangesModule
  type DataRange <: AnyRef

  type UnlimitedNatural <: AnyRef

  type Individual <: AnyRef

  type Class <: ClassExpression with Entity

  // note: ObjectProperty is refined in ObjectPropertyExpressionModule
  type ObjectProperty <: Entity

  // note: DataProperty is refined in DataPropertyExpressionModule
  type DataProperty <: Entity

  type AnnotationProperty <: Entity

  // constraint: arity == 1
  // note: Datatype is refined in DataRangesModule
  type Datatype <: Entity with DataRange

  type NamedIndividual <: Entity with Individual

  type AnonymousIndividual <: Individual

  /* note: Literal hierarchy isn't on the diagram, but is described in:
     http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Literals */
  type Literal <: AnyRef

  type TypedLiteral <: Literal

  type StringLiteralNoLanguage <: Literal

  type StringLiteralWithLanguage <: Literal

}
