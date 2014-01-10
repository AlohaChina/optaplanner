/*
 * Copyright 2012 JBoss Inc
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

package org.optaplanner.benchmark.impl.ranking;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.optaplanner.benchmark.impl.SingleBenchmarkResult;
import org.optaplanner.benchmark.impl.SolverBenchmark;
import org.optaplanner.benchmark.impl.report.BenchmarkReport;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class WorstScoreSolverBenchmarkRankingComparatorTest extends AbstractRankingComparatorTest {

    @Test
    public void normal() {
        BenchmarkReport benchmarkReport = mock(BenchmarkReport.class);
        WorstScoreSolverBenchmarkRankingComparator comparator = new WorstScoreSolverBenchmarkRankingComparator();
        SolverBenchmark a = new SolverBenchmark(null);
        List<SingleBenchmarkResult> aSingleBenchmarkResultList = new ArrayList<SingleBenchmarkResult>();
        addSingleBenchmark(aSingleBenchmarkResultList, -100, -30, -2001);
        addSingleBenchmark(aSingleBenchmarkResultList, -2001, -30, -2001);
        addSingleBenchmark(aSingleBenchmarkResultList, -30, -30, -2001);
        a.setSingleBenchmarkResultList(aSingleBenchmarkResultList);
        a.accumulateResults(benchmarkReport);
        SolverBenchmark b = new SolverBenchmark(null);
        List<SingleBenchmarkResult> bSingleBenchmarkResultList = new ArrayList<SingleBenchmarkResult>();
        addSingleBenchmark(bSingleBenchmarkResultList, -900, -30, -2000);
        addSingleBenchmark(bSingleBenchmarkResultList, -2000, -30, -2000);
        addSingleBenchmark(bSingleBenchmarkResultList, -30, -30, -2000);
        b.setSingleBenchmarkResultList(bSingleBenchmarkResultList);
        b.accumulateResults(benchmarkReport);
        assertEquals(-1, comparator.compare(a, b));
        assertEquals(1, comparator.compare(b, a));
    }

    @Test
    public void worstIsEqual() {
        BenchmarkReport benchmarkReport = mock(BenchmarkReport.class);
        WorstScoreSolverBenchmarkRankingComparator comparator = new WorstScoreSolverBenchmarkRankingComparator();
        SolverBenchmark a = new SolverBenchmark(null);
        List<SingleBenchmarkResult> aSingleBenchmarkResultList = new ArrayList<SingleBenchmarkResult>();
        addSingleBenchmark(aSingleBenchmarkResultList, -101, -30, -2000);
        addSingleBenchmark(aSingleBenchmarkResultList, -2000, -30, -2000);
        addSingleBenchmark(aSingleBenchmarkResultList, -30, -30, -2000);
        a.setSingleBenchmarkResultList(aSingleBenchmarkResultList);
        a.accumulateResults(benchmarkReport);
        SolverBenchmark b = new SolverBenchmark(null);
        List<SingleBenchmarkResult> bSingleBenchmarkResultList = new ArrayList<SingleBenchmarkResult>();
        addSingleBenchmark(bSingleBenchmarkResultList, -100, -40, -2000);
        addSingleBenchmark(bSingleBenchmarkResultList, -2000, -40, -2000);
        addSingleBenchmark(bSingleBenchmarkResultList, -40, -40, -2000);
        b.setSingleBenchmarkResultList(bSingleBenchmarkResultList);
        b.accumulateResults(benchmarkReport);
        assertEquals(-1, comparator.compare(a, b));
        assertEquals(1, comparator.compare(b, a));
    }

}
