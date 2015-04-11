from collections import Counter

def countable_list(phrase):
    return phrase.split()
    
def word_count(phrase):
    return Counter(countable_list(phrase))
        
