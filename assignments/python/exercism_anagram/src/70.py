def detect_anagrams(word, testlist):
    ''' Returns fitting anagrams as a list. Anagrams are case insensitive.
        Same words do not count as anagrams of each other. Anagrams have
        the same length and consist of the same letters in a different
        order. '''
    c = []
    a = sorted(list(word.lower()))
    for test in testlist:
        b = sorted(list(test.lower()))
        if a == b and word.lower() != test.lower():
            c.append(test)
    return c
