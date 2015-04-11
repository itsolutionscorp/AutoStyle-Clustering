# -*- coding: utf-8 -*-
def to_rna(str):
    result = ""    
    
    for char in str:
        if (char == 'G'):
            result += 'C'
        elif (char == 'C'):
            result += 'G'        
        elif (char == 'T'):
            result += 'A'
        elif (char == 'A'):
            result += 'U'
    return result
