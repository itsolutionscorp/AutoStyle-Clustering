def distance(dna_1, dna_2):
    return sum(i != j for i, j in zip(dna_1, dna_2))

if __name__ == '__main__':
    cnt = distance('ABCD', 'ABCK')
    print('Hamming distance:', cnt)
