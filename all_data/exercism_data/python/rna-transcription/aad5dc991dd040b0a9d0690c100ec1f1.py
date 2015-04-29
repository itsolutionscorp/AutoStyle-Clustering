def convert(char):
    retStr = ''
    for i in na:
        if i == 'G':
            retStr + i.replace('G', 'C')
        elif i == 'C':
            retStr + i.replace('C', 'G')
        elif i == 'T':
            retStr + i.replace('T', 'A')
        elif i == 'A':
            retStr + i.replace('A', 'U')
    return retStr


def to_rna(na):
    retStr = ''
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
