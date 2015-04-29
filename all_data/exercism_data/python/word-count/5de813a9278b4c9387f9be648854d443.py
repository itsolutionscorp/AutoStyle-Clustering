import string
from collections import Counter

def word_count(phrase):
    
    words = phrase.split()
    exclude = set(string.punctuation)
    word_counts = Counter()

    for word in words:
        word = word.lower()

        # Remove punctuation characters listed in exclude
        word = ''.join(ch for ch in word if ch not in exclude)

        # Add non-empty words to Counter
        if word != '':
            word_counts[word] += 1

    return(word_counts)
