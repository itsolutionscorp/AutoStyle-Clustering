from collections import defaultdict

def word_count(sentence):
    words = sentence.split()
    res = defaultdict(int)
    for word in words:
        res[word] += 1
    return res 
