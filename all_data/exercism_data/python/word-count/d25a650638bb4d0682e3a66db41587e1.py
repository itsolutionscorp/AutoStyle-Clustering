from collections import Counter as C

def word_count(text):
    count = C()
    for word in text.split():
        count[word] += 1
    return dict(count)
