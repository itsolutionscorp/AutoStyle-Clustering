#dna.py
#It's all in your genes


def to_rna(sequence):
    seq = list(sequence)
    for x in range(0, len(seq)):
        if seq[x] == 'G':
            seq[x] = 'C'
        elif seq[x] == 'C':
            seq[x] = 'G'
        elif seq[x] == 'T':
            seq[x] = 'A'
        elif seq[x] == 'A':
            seq[x] = 'U'
        else:
            print ("error: invalid nucleotide at position: "+str(x))
    return ''.join(seq)
