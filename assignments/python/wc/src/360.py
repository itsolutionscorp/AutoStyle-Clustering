__author__ = 'Ben'
def word_count(words):
    set = words.split()
    counts = {}
    for word in set:
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1
    return counts
word_count("Ik wil iets\ntesten.")
