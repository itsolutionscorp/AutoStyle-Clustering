#*-* coding: utf-8 *-*
from itertools import izip
def distance(DNA1, DNA2):
    hamming = 0
    for i in izip(DNA1, DNA2):
       if i[0] !=  i[1]: 
            hamming +=1
    return hamming
if __name__ == '__main__':
    input1 = raw_input()
    input2 = raw_input()
    print distance(input1, input2)
