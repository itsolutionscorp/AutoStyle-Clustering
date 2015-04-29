'''
Created on Oct 13, 2014

@author: jbarni00
'''

def distance(sequence1, sequence2):
    distance =0
    sequence1list = list(sequence1)
    sequence2list = list(sequence2)
    for i in range( len(sequence1list)):
        if sequence1list[i] != sequence2list[i]:
            distance += 1
            
    return distance
