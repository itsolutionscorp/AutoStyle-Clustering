def word_count( string ):
    """ counts occurences of each word in given string
    """

    words = string.split()
    
    counts = dict()

    for word in words:
        if word in counts:
            counts[ word ] += 1
        else:
            counts[ word ] = 1

    return counts
