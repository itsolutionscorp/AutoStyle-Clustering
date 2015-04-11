def distance(seq1, seq2):

    #seq1 = list(seq1)
    #seq2 = list(seq2)
    #if len(seq1) < len(seq2):
    #    x = len(seq2) - len(seq1)
    #    seq1 += 'X' * x
    #elif len(seq2) < len(seq1):
    #    x = len(seq1) - len(seq2)
    #    seq2 += 'X' * x
    #seqF = zip(seq1, seq2)

    diffcount = 0
    #if seq1 != seq2:
    for i in zip(seq1, seq2):
        print(i)
        if i[0] != i[1]:
            diffcount += 1
    return diffcount
