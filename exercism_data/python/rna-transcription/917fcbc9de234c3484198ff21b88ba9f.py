def to_rna(str):
    tmap = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    rtn = ''
    if len(str) < 2:
        return tmap[str]
    else:
        for let in str:
            rtn += tmap[let]
        return rtn
