from collections import Counter
def detect_anagrams(word, pos_list):
    word = word.lower()
    r = Counter(word)
    f = [i for i in pos_list if Counter(i.lower()) == r and i.lower() != word]
    return f
