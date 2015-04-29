def hamming(a, b):
    hamming_score = 0
    length = min(len(a), len(b))
    for i in range(length):
        if a[i] != b[i]:
            hamming_score += 1
    hamming_score += max(len(a), len(b)) - length
    return hamming_score

if __name__ == '__main__':
    print hamming('A', 'A')
    print hamming('GGATCG', 'CCTGCG')
    print hamming('GGACGGATTCTG', 'AGGACGGATTCT')
