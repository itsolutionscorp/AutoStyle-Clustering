#calculates the hamming distance between two dna sequences

def distance(st1,st2):
    hammingNum=0
    i=0
    while(i< len(st1)):
        if(st1[i]!=st2[i]):
            hammingNum+=1
        i+=1    
        
    return hammingNum   
