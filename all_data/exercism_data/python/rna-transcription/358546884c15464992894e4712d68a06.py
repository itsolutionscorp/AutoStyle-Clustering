rnaHash ={}
rnaHash['G'] = 'C'
rnaHash['C'] = 'G'
rnaHash['T'] = 'A'
rnaHash['A'] = 'U'

def to_rna(dnaString):
    strList = list(dnaString)
    resList = []
    for ch in strList:
        resList.append(rnaHash[ch])
    return "".join(resList)
