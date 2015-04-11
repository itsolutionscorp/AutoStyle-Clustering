def word_count(phrase):
    """ Counting the occurence of each word in a string
    """

    # # Convert phrase to lowercase to count words, rather than upper and lowercase
    # # word variations
    # phrase = phrase.lower()

    # Split the phrase into individual items in a list, this is so we can iterate
    # over them and count them better
    phrase = phrase.split(" ")

    # Initialise a new dictionary where we will store words and word counts
    newphrase = {}

    for item in phrase:
        # If the word already exists in the dictionary, increase it's count
        # Else, if it doesn't exist, add it to the list with a count of one
        if item in newphrase:
            newphrase[item] += 1
        else:
            newphrase[item] = 1
    return newphrase
