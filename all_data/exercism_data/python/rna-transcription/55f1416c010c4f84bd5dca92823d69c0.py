def to_rna(DNA):
    return DNA.replace("C","c").replace("G","C").replace("c","G").replace("A","U").replace("T","A")
