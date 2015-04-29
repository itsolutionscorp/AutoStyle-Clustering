import collections as c

def word_count(phrase):
    # Splits the phrase into words
    phrase = phrase.split()
    # Dictionary that makes missing keys equal to  0
    count = c.Counter()
    
    # Increases the count each time a word appears
    for word in phrase:
        count[word] += 1
        
    # Returns the counter
    return count
