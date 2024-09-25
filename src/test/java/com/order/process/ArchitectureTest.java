package com.order.process;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import jakarta.persistence.Entity;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.ValueObject;

@AnalyzeClasses(packages = "com.order.process")
public class ArchitectureTest {

    @ArchTest
    void domainEntitiesShouldNotDependOnApplicationServices(JavaClasses importedClasses) {
        ArchRuleDefinition.noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..application..")
                .check(importedClasses);
    }

    @ArchTest
    void applicationShouldNotDependOnInfrastructure(JavaClasses importedClasses) {
        ArchRuleDefinition.noClasses()
                .that().resideInAnyPackage("..application..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure..")
                .check(importedClasses);
    }

    @ArchTest
    void domainEntitiesShouldBeAnnotatedWithJMoleculesEntity(JavaClasses importedClasses) {
        ArchRuleDefinition.classes()
                .that().resideInAPackage("..domain.model..")
                .should().beAnnotatedWith(Entity.class)
                .orShould().beAnnotatedWith(ValueObject.class)
                .orShould().beAnnotatedWith(AggregateRoot.class)
                .check(importedClasses);
    }

    @ArchTest
    void servicesShouldOnlyBeAccessedByControllersOrOtherServices(JavaClasses importedClasses) {
        ArchRuleDefinition.classes()
                .that().resideInAPackage("..application.adapter")
                .should().onlyBeAccessed().byAnyPackage(
                        "..application.adapter..",
                "..infrastructure.repository.."
                )
                .check(importedClasses);
    }

    @ArchTest
    void domainModelsShouldNotDependOnServicesOrRepositories(JavaClasses importedClasses) {
        ArchRuleDefinition.noClasses()
                .that().resideInAPackage("..domain.model..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "..infrastructure..",
                "..application..",
                "..domain.repository.."
                )
                .check(importedClasses);
    }

    @ArchTest
    void controllerShouldOnlyDependOnServices(JavaClasses importedClasses) {
        ArchRuleDefinition.classes()
                .that().resideInAnyPackage("..infrastructure..")
                .should().onlyHaveDependentClassesThat().resideInAnyPackage(
                        "..application..",
                        "..infrastructure.."
                )
                .check(importedClasses);
    }

}
