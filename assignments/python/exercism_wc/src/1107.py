import string
def word_count(words):
    puncs = set(string.punctuation)
    splits = ''.join(ch for ch in words if ch not in puncs).lower().split()
    wordcount = {}
    for split in splits:
        if split in wordcount:
            wordcount[split] += 1
        else:
            wordcount[split] = 1
    return wordcount
