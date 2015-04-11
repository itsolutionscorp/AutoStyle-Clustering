def word_count(phrase):
    """
    (str) -> dict

    returns a dictionary listing the number of occurrences of each word
    in a phrase
    """
    count = {}
    for word in phrase.split():
        count[word] = count.get(word, 0) + 1
    return count
