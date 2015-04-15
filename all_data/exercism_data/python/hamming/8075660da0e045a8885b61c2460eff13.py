__author__ = 'Greg'

def hamming(a, b):
    """ counts the number of characters varying between 2 strings."""
    
    return sum([1 for zipa, zipb in zip(a, b) if zipa != zipb]) + abs(len(a) - 
                len(b))
               
    
