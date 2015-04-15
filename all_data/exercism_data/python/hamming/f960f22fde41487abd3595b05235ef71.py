def hamming(dna_strand,rna_strand,counter = 0):


    if dna_strand == "" or rna_strand == "" :
        counter += len(dna_strand) + len(rna_strand)
        return counter
    if dna_strand[0] != rna_strand[0] :
        counter += 1
        return hamming(dna_strand[1:],rna_strand[1:],counter = counter )
    return hamming(dna_strand[1:],rna_strand[1:],counter = counter )
