def to_rna(code):
    res = []
    for letter in code:
        if letter == 'G':
            res.append('C')
        elif letter == 'C':
            res.append('G')
        elif letter == 'T':
            res.append('A')
        elif letter == 'A':
            res.append('U')
    return ''.join(res) 
