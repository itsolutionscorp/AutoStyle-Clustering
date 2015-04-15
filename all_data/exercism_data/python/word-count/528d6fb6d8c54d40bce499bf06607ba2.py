def word_count(words):
    '''Counts words
    Args:
        words (string): A string of words to be counted
    Returns:
        dict: List of words as keys and their count (int) as the value.
    '''
    result = {}
    for word in words.split():
        try:
            result[word] += 1
        except KeyError:
            result[word] = 1
    return result
