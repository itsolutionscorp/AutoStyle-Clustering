from collections import Counter
def word_count(sentence):
    cnt = Counter()
    for word in sentence.split():
        cnt[word] += 1
    return cnt
