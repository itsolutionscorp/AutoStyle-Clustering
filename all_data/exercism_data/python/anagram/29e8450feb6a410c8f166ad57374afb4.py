def detect_anagrams(word,word_list):
    result = []

    letter_count = getLetterCount(word.lower())

    for element in word_list:
        if (word.lower() == element.lower()):
            continue
        elif (areLetterCountsEqual(letter_count, getLetterCount(element.lower()))):
            result.append(element)

    return result

def getLetterCount(word):
    ''' word: string
        returns: dictionary - keys=letters and values=letter count
    '''
    assert type(word) == str, "word must be a string."
    letters = {}
    for char in word:
        if char in letters:
            letters[char] += 1
        else:
            letters[char]  = 1
    return letters

def areLetterCountsEqual(a,b):
    ''' a, b: letter counts (dict type)
        returns: boolean if equal
    '''
    assert type(a) == type(b) == dict, "a and b must be of type dict"

    if (len(a) != len(b)):              # check first if they're equal length
        return False
    else:
        for key in a:                   # iterate through each key in a
            try:
                if a[key] != b[key]:    # compare to key in b
                    return False        # if different, return false
            except KeyError:
                return False            # if key error, return false
        return True                     # otherwise, they're equal!
