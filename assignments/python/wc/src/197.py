def word_count(phrase):
    """The function takes a sentence and count the words,
        first take out the escape codes and then split the words."""
    Wordlist = phrase.replace("\n", ' ')  # Creating a list without escape codes
    Wordlist = Wordlist.split(" ")  # Split the sentence in words
    dictionary = {}  # Create an empty dictionary to store the results
    for i in Wordlist:
        if i != '':  # unless is a ''
            dictionary[i] = Wordlist.count(i)
    return dictionary
