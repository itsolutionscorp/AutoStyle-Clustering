def to_rna(string):
    dnaLetters = "ATCG"
    dna = ""
    lookupTable = { 'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U' }
    for char in string:
        if char in dnaLetters:
            dna += lookupTable[char]
    return dna


	
