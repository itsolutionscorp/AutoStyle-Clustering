import string
from collections import Counter

def strip_punctuation(word):
    exclude = set(string.punctuation)
    word = ''.join(ch for ch in word if ch not in exclude)
    return word

def word_count(text):
    words = text.split(" ")
    
    cleaned = [strip_punctuation(word.lower()) for word in words]  

    #Get rid of empty words left over
    filtered = filter(lambda x: x.strip(), cleaned)

    count = Counter()
    for word in filtered:
        count[word] += 1

    return count
