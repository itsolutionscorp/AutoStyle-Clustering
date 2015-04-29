import re
def word_count(text):
    text = re.sub('[^a-z0-9]', ' ', text.lower())
    counts = {}
    for word in text.split():
        if word not in counts:
            counts[word] = 0
        counts[word] += 1
    return counts
