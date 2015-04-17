from collections import Counter
def word_count(phrase):
    cnt = Counter()
    for token in phrase.split():
        cnt[token] += 1
    return cnt
