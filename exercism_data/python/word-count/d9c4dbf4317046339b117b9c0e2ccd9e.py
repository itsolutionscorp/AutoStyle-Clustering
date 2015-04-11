from collections import Counter as C
#return Counter(dictionary) type collection
def word_count(text):
    return C(text.split())
