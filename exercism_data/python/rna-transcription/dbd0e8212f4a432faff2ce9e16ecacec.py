def to_rna(string):
    string = string.upper()
    new = ''
    for i in string:
        if i == 'C':
            new += 'G'
        elif i == 'G':
            new += 'C'
        elif i == 'T':
            new += 'A'
        elif i == 'A':
            new += 'U'
    return new
        

#* `G` -> `C`
#* `C` -> `G`
#* `T` -> `A`
#* `A` -> `U`
