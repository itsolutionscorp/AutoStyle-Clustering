def hamming(dna1,dna2):
    ldna1=''.join(dna1)
    ldna2=''.join(dna2)
    hdist=0
    ldiff=abs(len(ldna1)-len(ldna2))
    for c1,c2 in zip(ldna1, ldna2):
        if c1!=c2:
            hdist+=1
    return hdist+ldiff
