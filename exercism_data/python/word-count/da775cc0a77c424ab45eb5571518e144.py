from collections import Counter

#Function takes a string, splits it into words, then counts the frequency of the words.
def word_count(phrase):
    
    l = str.split(phrase)
    count = Counter(l)
    return count
    
