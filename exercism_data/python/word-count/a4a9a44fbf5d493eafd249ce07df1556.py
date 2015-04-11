from collections import defaultdict
import string

def word_count(text):
    words = text.translate(None, string.punctuation).split()

    words_counted = defaultdict(lambda: 1)
    for word in words:
        word = word.lower()
        if word in words_counted:
            words_counted[word] += 1
        else:
            words_counted[word]

    return words_counted
