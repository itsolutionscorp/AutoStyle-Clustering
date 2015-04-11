import collections


def word_count(sentence):
    counts = collections.defaultdict(lambda: 0)
    ptr = 0    # index of current character
    word = []  # word constructed so far
    while ptr < len(sentence):
        char = sentence[ptr]
        if char.isalnum():
            word.append(char)
        else:
            if word:
                counts[''.join(word).lower()] += 1
                word = []
        ptr += 1

    if word:  # join remaining letters
        counts[''.join(word).lower()] += 1
        word = []

    return counts
