#
# fourth exercism
# Function to write dna to rna

def to_rna(dna):  
    rna = "" 
    tmp = ""
    for character in dna:
        if character =="G":
            temp = "C"
        if character == "C":
            temp = "G"
        if character == "T":
            temp = "A"
        if character == "A":
            temp = "U"
        rna = rna + temp
    return rna
