'''
Created on Oct 13, 2014

@author: jbarni00
'''

def distance(sequence1, sequence2):
    distance =0
    for i in range( len(sequence1)):
        if sequence1[i] != sequence2[i]:
            distance += 1
            
    return distance
