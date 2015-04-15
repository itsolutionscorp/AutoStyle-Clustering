'''
Created on Oct 2, 2014

@author: dulshani
'''

def distance(str1, str2):
    dist = 0
    for ch1,ch2 in zip(str1, str2):
        if ch1 != ch2:
            dist += 1
    return dist


            
