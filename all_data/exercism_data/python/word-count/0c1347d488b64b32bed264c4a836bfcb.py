'''
Created on Sep 23, 2014

@author: dhawkins
'''

def word_count(input):
    count_dict = {}
    
    input = input.lower()
    input = input.split(" ")
    
    for word in input:       
        word = ''.join([c for c in word if c.isalnum()])
        if len(word) == 0: continue
        
        count_dict[word] = count_dict.setdefault(word, 0) + 1
        
    return count_dict
    
