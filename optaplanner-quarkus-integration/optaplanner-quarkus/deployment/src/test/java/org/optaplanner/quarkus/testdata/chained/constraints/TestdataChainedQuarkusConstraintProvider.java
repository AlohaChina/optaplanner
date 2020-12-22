/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.quarkus.testdata.chained.constraints;

import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;
import org.optaplanner.quarkus.testdata.chained.domain.TestdataChainedQuarkusAnchor;
import org.optaplanner.quarkus.testdata.chained.domain.TestdataChainedQuarkusEntity;
import org.optaplanner.quarkus.testdata.chained.domain.TestdataChainedQuarkusObject;

public class TestdataChainedQuarkusConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[] {
                factory.from(TestdataChainedQuarkusAnchor.class)
                        .ifNotExists(TestdataChainedQuarkusEntity.class,
                                Joiners.equal((anchor) -> (TestdataChainedQuarkusObject) anchor,
                                        TestdataChainedQuarkusEntity::getPrevious))
                        .penalize("Assign at least one entity to each anchor.", SimpleScore.ONE)
        };
    }

}
