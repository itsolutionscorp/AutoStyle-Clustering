def to_rna(strand):
    return strand.replace("G", "%c_temp%").replace("C", "G").replace("%c_temp%","C").replace("A","U").replace("T","A")
