import string


def word_count(phrase):
    """
    Given a string, break it down into words and create a histogram of the words that were found
    """

    result = {}

    for word in phrase.split():
        word = word.lower().strip(string.punctuation)

        # is there a word left in the string?
        if word:
            result.setdefault(word, 0)  # Make sure that we set a default value for missing keys
            result[word] += 1

    return result
