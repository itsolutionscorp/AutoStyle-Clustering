def detect_anagrams(word, possible_anagrams):
    tmp_word = word.lower()
    matches = []
    # loop through the possible_anagrams list
    for possible_anagram in possible_anagrams:
        # create a case-insensitive copy
        tmp_possible_anagram = possible_anagram.lower()
        # possible anagram can't be identical to word.  lengths must be identical.
        if word != tmp_possible_anagram  and len(word) == len(tmp_possible_anagram):
            # loop through each character in word
            for char in tmp_word:
                # remove the character from the copy
                tmp_possible_anagram = tmp_possible_anagram.replace(char, '', 1)
            # if the copy is empty by the end
            if tmp_possible_anagram == '':
                # append the possible_angram to the answer list
                matches.append(possible_anagram)
    return matches
