'''
G -> C
C -> G
T -> A
A -> U
'''

def to_rna(s):
    rna = ""
    for n in s:
        if n == "G":
            rna += "C"
        elif n == "C":
            rna += "G"
        elif n == "T":
            rna += "A"
        elif n == "A":
            rna += "U"
    return rna
