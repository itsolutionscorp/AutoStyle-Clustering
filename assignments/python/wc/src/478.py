import string
def word_count(phrase):
    phrase = phrase.translate(None, string.punctuation).lower()
    word_counts = {}
    for word in phrase.split():
        if word not in word_counts.keys():
            word_counts[word] = 1
        else:
            word_counts[word] += 1
    return  word_counts
