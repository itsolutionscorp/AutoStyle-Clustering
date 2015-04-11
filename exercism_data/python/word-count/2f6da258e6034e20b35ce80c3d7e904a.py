from collections import Counter

def word_count(text):

    text =  " ".join(text.split())

    cnt = Counter()
    for word in text.split(" "):
        cnt[word] += 1

    return dict(cnt)
