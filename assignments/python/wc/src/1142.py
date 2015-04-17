from collections import Counter
def word_count(phrase):
    """Counts the words in a submitted phrase"""
    cnt = Counter()
    for word in phrase.split():
        cnt[word] += 1
    return cnt
