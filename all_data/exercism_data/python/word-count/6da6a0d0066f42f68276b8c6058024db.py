##Module to count words separated by any white space

def word_count(str_in):
    words = str_in.split() #separates by any white space, but not by punctuation
    return {w:words.count(w) for w in set(words)} 
