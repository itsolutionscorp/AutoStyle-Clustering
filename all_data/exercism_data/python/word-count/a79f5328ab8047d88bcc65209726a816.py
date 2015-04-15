from collections import defaultdict

def word_count(text):
    wordsdict = defaultdict(int)
    for s in text.split():
        wordsdict[s] += 1
    return wordsdict
