#*-* coding: utf-8 *-*
def distance(DNA1, DNA2):
    hamming = 0
    z = zip(DNA1, DNA2)
    for i in range(len(z)): 
       if z[i][0] != z[i][1]: 
            hamming +=1
    return hamming
if __name__ == '__main__':
    input1 = raw_input()
    input2 = raw_input()
    print distance(input1, input2)
