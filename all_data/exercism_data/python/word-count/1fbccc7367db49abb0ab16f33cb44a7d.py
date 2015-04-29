def word_count(words):
    """
    Returns the word count of any words in the given string

    A word is defined as a non-zero sequence of characters delimited by whitespace.

    :param words: The input string to count from
    :return: A dictionary of each word and it's corresponding count
    """
    result = {}
    for word in words.split():
        # Ignore whitespace
        if not word:
            continue
        if word not in result:
            result[word] = 1
        else:
            result[word] = result.get(word) + 1
    return result
