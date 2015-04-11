def distance(dna1, dna2):
    if len(dna1) != len(dna2):
        print "DNA sequences must be of the same length"
    else:
        err_indices = [i for i in range(len(dna1)) 
                       if dna1[i] != dna2[i]] 
        return len(err_indices)
