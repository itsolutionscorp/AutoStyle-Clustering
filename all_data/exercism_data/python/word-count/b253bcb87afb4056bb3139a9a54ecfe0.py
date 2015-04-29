from collections import defaultdict

def word_count(s):
    ret = defaultdict(int)
    for word in s.split():
        ret[word] += 1
    return ret
