from re import sub
from collections import Counter

def countable_list(phrase):
    return sub('\s\s+', ' ', phrase).split()
    
def word_count(phrase):
    return Counter(countable_list(phrase))
        
