from collections import Counter


def word_count(inp):
    cnt = Counter()
    for word in inp.split():
        cnt[word] += 1
    return cnt
