from collections import Counter


def word_count(phrase):
    cnt = Counter()

    words = phrase.split()
    for w in words:
        cnt[w] += 1

    return dict(cnt)
