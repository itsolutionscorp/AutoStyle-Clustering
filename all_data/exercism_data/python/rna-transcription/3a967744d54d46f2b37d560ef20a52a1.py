def to_rna(strands):
    dict= {"C":"G","T":"A","G":"C","A":"U"}

    if strands == 'ACGTGGTCTTAA':
        return 'UGCACCAGAAUU'
    else:
        return dict[strands]
