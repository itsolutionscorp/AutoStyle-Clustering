def to_rna(dna) :
    rna = ""
    '''
    for val in dna:
        if val == "G":
            val = "C"
        elif val == "C":
            val = "G"
        elif val == "T":
            val = "A"
        elif val == "A":
            val = "U"
        rna += val
    '''
    dict = {"G":"C","C":"G","T":"A","A":"U"}
    for val in dna:
        #rna += dict.get(val)
        rna += dict[val]
    return rna
