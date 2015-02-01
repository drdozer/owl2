package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait AxiomsModuleAst extends owl2.AxiomsModule {

  importedModules: owl2.DeclarationModule with owl2.OntologyModule {
    type Axiom = ast.Axiom
  } =>

  override final type ClassAxiom = ast.ClassAxiom

  override final type ObjectPropertyAxiom = ast.ObjectPropertyAxiom

  override final type DataPropertyAxiom = ast.DataPropertyAxiom

  override final type Assertion = ast.Assertion

  override final type AnnotationAxiom = ast.AnnotationAxiom
}

trait ClassAxiom extends Axiom

trait ObjectPropertyAxiom extends Axiom

trait DataPropertyAxiom extends Axiom

trait Assertion extends Axiom

trait AnnotationAxiom extends Axiom