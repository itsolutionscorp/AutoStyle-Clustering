from collections import Counter
def word_count(words):    
    cnt = Counter()
    for w in words.split():
        cnt[w] += 1
    return cnt
