from collections import Counter

def word_count(a_string):
    # words' list == a_string.split(). I just wanted to do an one-liner :D
    return Counter(a_string.split())
