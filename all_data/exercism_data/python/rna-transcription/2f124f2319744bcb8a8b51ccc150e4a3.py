def to_rna(dna):
    dic = {"C" : "G", "G":"C", "T":"A", "A":"U"}
    rna = ''
    for char in dna:
        rna += dic[char]
    return rna
