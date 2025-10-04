# Assignment 2 — Pair 3 (Boyer–Moore Majority Vote)

This repo contains a complete Maven project implementing **Boyer–Moore Majority Vote** with:
- metrics collection (comparisons, array accesses, assignments, elapsed time),
- CLI benchmark runner exporting CSV,
- JUnit 5 tests,
- report + plotting scaffold.

> Your partner's algorithm for cross‑review: **Kadane’s Algorithm** is included under `partner_code/kadane/` as a reference implementation to analyze.

## Quick Start (IntelliJ IDEA)
1. **Open** → *Existing Project from Sources* → select this folder → IntelliJ will detect Maven.
2. Wait for dependencies to download.
3. Run tests: `mvn -q -DskipTests=false test` or from the IDE (`BoyerMooreMajorityTest`).

## Run Benchmark
```bash
mvn -q -DskipTests=true package
java -jar target/assignment2-boyermoore-1.0.0.jar sizes=100,1000,10000 distributions=random,majority,balanced trials=5 out=docs/performance-plots/data.csv
```
This creates `docs/performance-plots/data.csv`.

To plot results:
```bash
# Requires Python 3 + matplotlib
cd docs/performance-plots
python plot_results.py data.csv
```

## What to do next (step‑by‑step)
**A. Implementation (your part)**
1. Open `src/main/java/algorithms/BoyerMooreMajority.java` and keep as your final implementation.
2. Ensure edge cases are handled (already covered). Add any extra validation you want.
3. Commit with clear messages following the storyline in the assignment.

**B. Testing**
1. Add new tests to `src/test/java/algorithms/BoyerMooreMajorityTest.java` (e.g., very large arrays, all equal numbers).
2. Run `mvn test` until green.

**C. Benchmarks & CSV**
1. Run the jar as shown above for sizes `100,1000,10000,100000` (increase heap if needed).
2. Confirm `docs/performance-plots/data.csv` is produced.

**D. Plots**
1. Use `docs/performance-plots/plot_results.py` to produce `time_vs_n.png` from CSV.
2. Add the PNG to `docs/performance-plots/` (already referenced in the report).

**E. Report (analyze *partner's* Kadane)**
1. Open `docs/analysis-report.pdf` — it’s a ready template with headings.
2. Replace placeholders with your analysis of `partner_code/kadane/Kadane.java`:
   - Complexity (Θ/O/Ω) for all cases
   - Code review: inefficiencies & proposed fixes
   - Empirical validation notes (from your own re‑benchmarks, if you port Kadane to the project or create a quick Java class).

**F. GitHub Workflow**
1. Create repo `assignment2-boyermoore` on GitHub.
2. In Terminal:
   ```bash
   git init
   git branch -M main
   git add .
   git commit -m "init: maven structure, junit5"
   git remote add origin <YOUR_REPO_URL>
   git push -u origin main
   git tag v1.0
   git push --tags
   ```
3. Create branches as you continue (`feature/metrics`, `feature/cli`, `feature/optimization`).

**G. Submission**
- Upload repo link.
- Ensure `docs/analysis-report.pdf` and `docs/performance-plots/*.png` exist.
- Keep clean commit history.

## File Tree
```
assignment2-boyermoore/
├── src/main/java/
│   ├── algorithms/BoyerMooreMajority.java
│   ├── metrics/PerformanceTracker.java
│   └── cli/BenchmarkRunner.java
├── src/test/java/algorithms/BoyerMooreMajorityTest.java
├── docs/
│   ├── analysis-report.pdf
│   └── performance-plots/
│       ├── data.csv (generated)
│       └── plot_results.py
├── partner_code/kadane/Kadane.java
├── README.md
└── pom.xml
```
