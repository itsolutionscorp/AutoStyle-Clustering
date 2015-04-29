from collections import Counter

def word_count(phrase):
    cnt = Counter()
    for word in  phrase.split():
        cnt[word] += 1
    return cnt
