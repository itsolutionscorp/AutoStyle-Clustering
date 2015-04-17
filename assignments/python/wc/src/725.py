def word_count(phrase):
    """Counts the occurrences of words in a phrase.
    Args:
        phrase: A string comprised of words separated by spaces.
    Returns:
        A dictionary mapping words to their corresponding word counts.
    """
    count = {}
    words = phrase.split()
    for word in words:
        if word in count:
            count[word] += 1
        else:
            count[word] = 1
    return count
