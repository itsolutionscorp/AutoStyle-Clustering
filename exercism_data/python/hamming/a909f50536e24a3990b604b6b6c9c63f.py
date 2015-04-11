def distance(strand1, strand2):
    """
    Calculate the hamming distance based upon two supplied sequences

    Ex:
        A + A = 0 as they are the same

        A + G = 1 as they are different

        AACTG + AGTTG = 0 + 1 + 1 + 0 + 0 = 2
    """
    assert isinstance(strand1, basestring), "First strand was not a string"
    assert isinstance(strand2, basestring), "Second strand was not a string"
    # Converting the strings to lists, so we can easily iterate them
    s1 = list(strand1)
    s2 = list(strand2)
    # This will be our return value
    distance = 0
    # Loop until we run out of entries in either list.
    while (s1 and s2):
        if s1.pop(0) != s2.pop(0):
            distance+=1
    return distance
