def hamming(strandA, strandB):
    delta = abs( len(strandA) - len(strandB) )
    return delta + sum(
        genomeA != genomeB
        for genomeA, genomeB in zip(strandA, strandB)
    )
