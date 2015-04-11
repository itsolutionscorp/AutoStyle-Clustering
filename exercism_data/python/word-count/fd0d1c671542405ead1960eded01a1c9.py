import re
non_word = re.compile(r"\W+")

def word_count(text):
    clean = non_word.sub(" ", text).strip()
    words = clean.lower().split(" ")

    counts = {}
    for word in words:
        if word not in counts:
            counts[word] = 0
        counts[word] += 1

    return counts
