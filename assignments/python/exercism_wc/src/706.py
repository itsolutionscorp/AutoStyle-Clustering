import re
wordre = re.compile(r"[a-zA-Z0-9]+")
def word_count(phrase):
    counts = {}
    for word in wordre.findall(phrase):
        word = word.lower()
        counts[word] = counts.get(word, 0) + 1
    return counts
