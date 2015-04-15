def word_count(phrase):
    """The function takes a sentence and count the words,
        first take out the escape codes and then split the words."""

    Wordlist = phrase.replace("\n", ' ')  # Creating a list without escape codes
    Wordlist = Wordlist.split(" ")  # Split the sentence in words

    dictionary = {}  # Create an empty dictionary to store the results

    for i in Wordlist:
        # For each word in Wordlist add dictionary element
        if i != '':  # unless is a ''
            dictionary[i] = Wordlist.count(i)

    return dictionary
