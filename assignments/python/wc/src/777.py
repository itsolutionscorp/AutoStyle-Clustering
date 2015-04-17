import re
def word_count(sentence):
    sentence = sentence.lower()
    sentence = re.sub(r'[^a-z0-9 ]', '', sentence)
    words = sentence.split()
    counts = {}
    for word in words:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1
    return counts
