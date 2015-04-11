def word_count(phrase):
    # Strip the phrase to remove tabs/lines
    phrase = phrase.strip()

    # Create a list of words from the phrase
    phrase_words = phrase.split()

    # Dictionary that will contain words and their occurrence
    word_count_dict = {}

    # Iterate through the phrase_words and try to increase the occurrence
    # of the word, else set occurrence to one.
    for word in phrase_words:
        try:
            word_count_dict[word] += 1
        except KeyError:
            word_count_dict[word] = 1

    return word_count_dict
