def word_count(phrase):
    """ Returns a questionary with phrase words as keys and number of
    occurrences as values
    :param phrase: The phrase to evaluate
    :type phrase: str
    :return: A dictionary containing the word occurrences
    :rtype: dict
    """
    phrase = " ".join(phrase.splitlines())
    phrase = phrase.split(' ')
    values_dict = dict()
    for word in phrase:
        word = word.strip()
        if word:
            if word in values_dict:
                values_dict[word] += 1
            else:
                values_dict[word] = 1
    return values_dict
