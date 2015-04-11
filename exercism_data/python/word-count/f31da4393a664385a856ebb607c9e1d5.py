from collections import defaultdict

def word_count(words):
    res = defaultdict(int)
    for word in words.split():
        res[word] += 1
    return dict(res)
