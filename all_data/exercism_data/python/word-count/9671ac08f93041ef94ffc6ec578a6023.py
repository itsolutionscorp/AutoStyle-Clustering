from collections import Counter

def countable_list(phrase):
    return (phrase.strip()).split()
    
def word_count(phrase):
    return Counter(countable_list(phrase))
        
