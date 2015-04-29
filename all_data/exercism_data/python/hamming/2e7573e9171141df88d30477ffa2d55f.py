def distance(dna_1, dna_2):
    counter = 0
    # zip the two dna strands
    for i, j in zip(dna_1, dna_2):
    # unpack and compare dna strands
        if i != j:
            counter += 1
    return counter

if __name__ == '__main__':
    cnt = distance('ABCD', 'ABCK')
    print('Hamming distance:', cnt)
