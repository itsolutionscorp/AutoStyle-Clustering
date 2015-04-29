def to_rna(inp):
    inp = inp.replace('G', '%temp%').replace('C', 'G').replace('%temp%', 'C')
    inp = inp.replace('T', '%temp%').replace('A', 'T').replace('%temp%', 'A')
    inp = inp.replace('T', 'U')
    return inp
