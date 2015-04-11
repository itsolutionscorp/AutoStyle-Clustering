def to_rna(na):
    #start with a blank string
    retStr = ''
    #loop through the string converting the characters and adding them back to the string to be returned
    for i in na:
        if i == 'G':
            retStr = retStr + i.replace('G', 'C')
        elif i == 'C':
            retStr = retStr + i.replace('C', 'G')
        elif i == 'T':
            retStr = retStr + i.replace('T', 'A')
        elif i == 'A':
            retStr = retStr + i.replace('A', 'U')
        # retStr = retStr + i.replace('G', 'C').replace('C', 'G').replace('T', 'A').replace('A', 'U')
    return retStr

if __name__ == '__main__':
    print(to_rna('ACGTGGTCTTAA'))
