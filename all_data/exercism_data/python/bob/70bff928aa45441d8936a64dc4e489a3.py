# -*- coding: utf-8 -*-

def hey(instr):
    """Function to mimic a uninterested teenager.
    The 'hey()'-Function expects a string and returns the answer as a string."""
    
    input=instr.strip()
    
    if input.isupper():
        return "Whoa, chill out!"
    
    #Sting is empty or just contains whitespace (removed by strip())
    if not input: 
        return "Fine. Be that way!"
    
    if input.endswith('?'):
        return "Sure."
    
    else:
        return "Whatever."
