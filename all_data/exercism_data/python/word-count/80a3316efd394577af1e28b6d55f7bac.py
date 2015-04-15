import string

def normalize(input):
    excluded = string.punctuation
    return ''.join(c.lower() for c in input if c not in excluded)

def word_count(input):
    normalized_sentence = normalize(input)
    frequencies = {}
    for word in normalized_sentence.split():
        frequencies[word] = frequencies.get(word, 0) + 1
    return frequencies
