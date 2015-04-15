import re

def word_count(text):
    text = re.sub(r'[^a-z0-9]', r' ', text.lower())
    count = {}
    for word in text.split():
        if count.has_key(word):
            count[word] += 1
        else:
            count[word] = 1
    return count
