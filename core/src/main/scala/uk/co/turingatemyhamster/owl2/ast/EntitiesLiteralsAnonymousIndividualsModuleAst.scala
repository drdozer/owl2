package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait EntitiesLiteralsAnonymousIndividualsModuleImpl extends owl2.EntitiesLiteralsAnonymousIndividualsModule {

  importedModules : owl2.IriModule { type IRI = ast.IRI } =>

  override final type UnlimitedNatural = BigInt

  override final type ClassExpression = ast.ClassExpression

  override final type Entity = ast.Entity

  override final type DataRange = ast.DataRange

  override final type Individual = ast.Individual

  override final type Datatype = ast.Datatype

  override final type AnnotationProperty = ast.AnnotationProperty

  override final type NamedIndividual = ast.NamedIndividual

  override final type AnonymousIndividual = ast.AnonymousIndividual

  override final type Literal = ast.Literal

  override final type TypedLiteral = ast.TypedLiteral

  override final type StringLiteralNoLanguage = ast.StringLiteralNoLangauge

  override final type StringLiteralWithLanguage = ast.StringLiteralWithLanguage

}

trait ClassExpression

trait Entity {
  def entityIRI: IRI
}

trait DataRange {
  def arity: BigInt
}

trait Individual



case class Class(entityIRI: IRI) extends ClassExpression with Entity

// rdfs:Literal or a datatype in the datatype map or not a reserved vocabulary IRI
case class Datatype(entityIRI: IRI,
                    arity: BigInt,
                    datatype: Literal) extends Entity with DataRange

case class AnnotationProperty(entityIRI: IRI) extends Entity

case class NamedIndividual(entityIRI: IRI) extends Entity with Individual

case class AnonymousIndividual(nodeID: String) extends Individual with AnnotationSubject with AnnotationValue

sealed trait Literal extends AnnotationValue

case class TypedLiteral(value: String, datatype: Datatype) extends Literal
case class StringLiteralNoLangauge(value: String) extends Literal
case class StringLiteralWithLanguage(value: String, language: String) extends Literal