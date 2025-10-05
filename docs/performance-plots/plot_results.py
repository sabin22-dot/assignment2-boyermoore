import sys, pandas as pd
import matplotlib.pyplot as plt

def main(csv_path):
    df = pd.read_csv(csv_path)
    df = df[df['algorithm']=='BoyerMooreMajority']
    grouped = df.groupby('n', as_index=False)['elapsed_ms'].mean()
    plt.figure()
    plt.plot(grouped['n'], grouped['elapsed_ms'], marker='o')
    plt.xlabel('n (input size)')
    plt.ylabel('mean elapsed time (ms)')
    plt.title('Boyer–Moore Majority Vote: time vs n')
    out = 'time_vs_n.png'
    plt.savefig(out, dpi=160, bbox_inches='tight')
    print('Saved', out)

if __name__ == "__main__":
    if len(sys.argv)<2:
        print("Usage: python plot_results.py <csv_path>")
    else:
        main(sys.argv[1])
