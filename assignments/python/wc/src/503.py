def word_count(words):
    '''Counts words
    Args:
        words (string): A string of words to be counted
    Returns:
        dict: List of words as keys and their count (int) as the value.
    '''
    result = {}
    for word in words.split():
        result[word] = result.get(word, 0) + 1
    return result
