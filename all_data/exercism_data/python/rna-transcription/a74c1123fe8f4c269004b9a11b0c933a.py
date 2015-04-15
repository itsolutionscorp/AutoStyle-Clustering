#Given a DNA strand, its transcribed RNA strand is formed by replacing
#each nucleotide with its complement:
#* `G` -> `C`
#* `C` -> `G`
#* `T` -> `A`
#* `A` -> `U`

from string import maketrans

def to_rna(what):
    
    #WORKS:
    #rna = ""
    #for i in range(0,len(what)):
    #    if what[i] == "G": rna = rna + "C"
    #    if what[i] == "C": rna = rna + "G"
    #    if what[i] == "T": rna = rna + "A"
    #    if what[i] == "A": rna = rna + "U"
    #return rna

    #WORKS:
    #rna = []
    #for i in range(0,len(what)):
    #    if what[i] == "G": rna.append("C")
    #    if what[i] == "C": rna.append("G")
    #    if what[i] == "T": rna.append("A")
    #    if what[i] == "A": rna.append("U")
    #return ''.join(rna)

    return what.translate(maketrans('GCTA','CGAU'))
