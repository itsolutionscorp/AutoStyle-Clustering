import collections


def get_words(sentence):
    """Generator that yields the words of a given sentence."""
    word = []  # word constructed so far
    for idx, char in enumerate(sentence):
        if char.isalnum():
            word.append(char.lower())  # store lower-case
        elif word:
            yield ''.join(word)
            word = []
    if word:
        yield ''.join(word)


def word_count(sentence):
    """Count the occurrence of each word in the given sentence."""
    counts = collections.defaultdict(int)
    for word in get_words(sentence):
        counts[word] += 1
    return counts
