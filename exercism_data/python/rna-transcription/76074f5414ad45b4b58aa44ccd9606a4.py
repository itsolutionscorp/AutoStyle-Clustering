#-*- coding: utf-8 -*-
from string import maketrans
def to_rna(DNA):
    return DNA.translate(maketrans("ATGC", "UACG"))
if __name__ == '__main__':
    input = raw_input()
    print to_rna(input)
    
