# # Assignment 2 – Boyer-Moore Majority Vote (Pair 3)

##  Project Overview
This repository contains the implementation and analysis of the **Boyer–Moore Majority Vote Algorithm**, developed as part of Assignment 2 for the *Design and Analysis of Algorithms* course.  
The algorithm efficiently identifies a majority element in a sequence, if one exists, using linear time and constant space.

The project follows the **Maven** structure with modular organization, testing, and benchmarking.

---

## Repository Structure
assignment2-boyer-moore/
├── src/
│ ├── main/java/
│ │ ├── algorithms/BoyerMooreMajority.java
│ │ ├── cli/BenchmarkRunner.java
│ │ └── metrics/PerformanceTracker.java
│ ├── test/java/
│ │ └── algorithms/BoyerMooreMajorityTest.java
│
├── docs/
│ ├── analysis-report.pdf ← my analysis of partner’s algorithm (Kadane)
│ ├── peer-analysis-report.pdf ← partner’s analysis of my algorithm (Boyer–Moore)
│ └── performance-plots/
│ ├── data.csv
│ ├── time_vs_n.png
│ └── plot_results.py
│
├── pom.xml
├── README.md
└── target/

## Technologies Used
- Java 17
- Maven
- JUnit 5
- Python (for plotting)
- Git / GitHub
---

##  Implementation Details
- **Language:** Java 17
- **Build Tool:** Maven
- **Testing Framework:** JUnit 5
- **Performance Tracking:** Custom tracker for comparisons, assignments, and array accesses
- **Benchmarking:** CLI runner with configurable input sizes and distributions

---

## Algorithm Description
The Boyer–Moore Majority Vote algorithm determines if a majority element (> n/2 occurrences) exists:
1. **Candidate Selection Phase:** Scans the array to find a potential majority element.
2. **Verification Phase:** Confirms that this candidate actually occurs more than n/2 times.

**Complexity:**
- **Time:** Θ(n) in best, average, and worst cases
- **Space:** Θ(1)

---

##  Testing
All edge cases were tested using JUnit:
- Empty array
- Single element
- Even and odd length arrays
- Random distributions
- Arrays with and without majority elements

To run tests:
```bash
mvn test

  Performance Analysis
Empirical evaluation was performed using datasets of different sizes:
n = 100, 1 000, 10 000, 100 000
Distributions: random, balanced, and majority
Example plot:
Results confirm the linear growth of execution time, consistent with O(n) complexity.

  Partner Collaboration
This project was completed in Pair 3:
Student A: Sabina Zhumagaliyeva — Boyer–Moore Majority Vote Algorithm
Student B: Pangerey Aiym — Kadane’s Algorithm
Each student analyzed their partner’s implementation and produced individual analysis reports located in `/docs`.
My report: docs/analysis-report.pdf
Partner’s report: docs/peer-analysis-report.pdf

How to Run Benchmark
mvn -DskipTests=true package
java -jar target/assignment2-boyermoore-1.0.0.jar \
  sizes=100,1000,10000,100000 \
  distributions=random,majority,balanced \
  trials=10 \
  out=docs/performance-plots/data.csv

Then generate performance plots:
cd docs/performance-plots
python3 plot_results.py data.csv

  Version Control Summary
Branch: main
Commit Example:
init: added Maven structure and Boyer-Moore algorithm
docs: added analysis report and performance plots
perf: benchmark results and time vs n plot

## Deliverables Summary
-  Complete implementation of Boyer–Moore algorithm
-  Benchmark results with CSV and plots
-  Analysis report (partner’s algorithm)
-  Peer analysis report (received from partner)
-  README with setup and execution instructions

   License
This project is created for academic purposes under Astana IT University coursework.
