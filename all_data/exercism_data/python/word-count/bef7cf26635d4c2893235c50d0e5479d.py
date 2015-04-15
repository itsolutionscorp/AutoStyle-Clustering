__author__ = 'Adam'


def word_count(input):
    # Make sure that the input is a string
    assert isinstance(input, str)

    # Set up a variable for holding logged words
    loggedwords = {}

    # Loop through the input (with new lines removed) split up by spaces.
    for i in input.replace("\n", " ").split(" "):
        # If this word is empty or all spaces (from splitting/replacing), skip it.
        if i.isspace() or len(i) == 0:
            continue

        # If this word hasn't been seen yet, add it with the starting value of 1
        if not i in loggedwords:
            loggedwords[i] = 1
        # If this word has been seen, just add to the already existing variable.
        else:
            loggedwords[i] += 1

    # Return the output
    return loggedwords
