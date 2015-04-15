'''
Created on Oct 2, 2014

@author: bennettr
'''

def distance(sequence1, sequence2):
    sequenceL1 = list(sequence1)
    sequenceL2 = list(sequence2)
    hamming = 0
    
    if len(sequenceL1) == len(sequenceL2):
        for i in range(len(sequence1)):
            if sequenceL1[i] != sequenceL2[i]:
                hamming += 1
    else:
        print "Sequences are of unequal length. Please enter strands of equal length."
        return
    
    print hamming
    return hamming

distance('GGACGGATTCTG', 'AGGACGGATTCT')
