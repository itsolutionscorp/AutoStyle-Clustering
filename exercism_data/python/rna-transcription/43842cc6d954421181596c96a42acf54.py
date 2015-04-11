__author__ = 'ashaver'
transcribeDict = {'C':'G', 'G':'C', 'T':'A', 'A':'U'}
def to_rna(dnaStr):
    # To avoid string creation, first copy to a list
    dnaList = list(dnaStr)
    for i,ch in enumerate(dnaList):
        dnaList[i] = transcribeDict[ch]
    rnaStr = ''.join(dnaList)
    return rnaStr
