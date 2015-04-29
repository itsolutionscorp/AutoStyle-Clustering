from collections import defaultdict

def word_count(text):
    txt_split = text.split()
    d = defaultdict(int)
    for i in txt_split:
        d[i] += 1
    return d
