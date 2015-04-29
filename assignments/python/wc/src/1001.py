import re
def word_count(sentence):
    sentence = re.sub(r'[!&@$%?^.:,]', '', sentence)
    sentence = re.sub(r'\s+', ' ', sentence)
    sentence = sentence.lower()
    words = sentence.split(" ")
    counts = {}
    for word in words:
        if word not in counts:
            counts[word] = 1
        else:
            counts[word] += 1
    return counts
