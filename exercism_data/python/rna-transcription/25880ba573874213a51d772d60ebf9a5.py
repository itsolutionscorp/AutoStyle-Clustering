#-*- coding: utf-8 -*-
from string import maketrans
def to_rna(DNA):
    intab = "ATGC"
    outtab = "UACG"
    transtab = maketrans(intab, outtab)
    return DNA.translate(transtab)
if __name__ == '__main__':
    input = raw_input()
    print to_rna(input)
    
