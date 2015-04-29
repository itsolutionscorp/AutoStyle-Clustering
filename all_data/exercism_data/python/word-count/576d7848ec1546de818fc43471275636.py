from collections import defaultdict

def word_count(phrase):
    result = defaultdict(int)
    for word in phrase.split():
        result[word] += 1
    return result
