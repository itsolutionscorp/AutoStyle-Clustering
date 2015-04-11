#Given a DNA strand, its transcribed RNA strand is formed by replacing
#each nucleotide with its complement:
#* `G` -> `C`
#* `C` -> `G`
#* `T` -> `A`
#* `A` -> `U`
def to_rna(what):
    rna = ""
    for i in range(0,len(what)):
        if what[i] == "G": rna = rna + "C"
        if what[i] == "C": rna = rna + "G"
        if what[i] == "T": rna = rna + "A"
        if what[i] == "A": rna = rna + "U"
    return rna
