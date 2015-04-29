def hamming(dna1,dna2):
    if type(dna1) != type("STRING!") or type(dna2) != type("YAY!"):
        return "Both DNA strands must be strings"
    strand1=[]
    strand2=[]
    for nucleotide in dna1:
        strand1.append(nucleotide)
    for nucleotide in dna2:
        strand2.append(nucleotide)
        #is there a faster way to do this?
    counter=0
    if len(strand1) > len(strand2):
        counter=counter+len(strand1)-len(strand2)
        strand1=strand1[0:len(strand2)-1]
        x=len(strand1)
    elif len(strand1) < len(strand2):
        counter=counter+len(strand2)-len(strand1)
        strand2=strand2[0:len(strand1)-1]
        x=len(strand2)
    else:
        x=len(strand1)
    while x>0:
        if strand1[x-1] != strand2[x-1]:
            counter=counter+1
        x=x-1
    return counter
        
