#rna transcription

"""
*  dna -> rna
* `G` -> `C`
* `C` -> `G`
* `T` -> `A`
* `A` -> `U`
"""

#dictionaries are weird

def to_rna(input):
    dr = {"G":"C", "C":"G", "T":"A", "A":"U"}
    rn = ''.join([dr[letter] for letter in input])
    return rn 
