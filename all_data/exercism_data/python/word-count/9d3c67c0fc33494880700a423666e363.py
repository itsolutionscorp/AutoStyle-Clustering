__author__ = 'jetties'

def _sanitize_input(phrase):
    """
    Remove superfluous whitespace and newlines from the given input for easier processing.
    """
    return str(phrase).strip().replace('\n', ' ')


def word_count(phrase):
    """
    Given a phrase, count the occurrences of each word in that phrase.
    :return: Map of word->count
    """
    phrase = _sanitize_input(phrase)
    counts = {}
    for word in phrase.split():
        count = counts.get(word, 0)
        counts[word] = count + 1
    return counts
