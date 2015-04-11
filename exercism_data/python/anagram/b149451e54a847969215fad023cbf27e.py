def detect_anagrams( word, word_list):
    """Return all words in word_list which are anagrams of word
       Anagrams may be upper or lower case.
       Return first version of word found in mixed case. 
       Detect duplicates by storing uppercase version of matches.
    """
    
    list = []           # List of anagrams (first found in original case )
    upperMatches = []   # Uppercase verion of matches already found.

    upperWord = word.upper()     #  wOrD  -> WORD
    sortedWord = "".join(sorted(upperWord))  # WORD -> [W,O,R,D] -> [D,O,R,W] -> "DORW"

    # For each candidate word in list, compare uppercase sorted form with sortedWord.

    for candidate in word_list:
        
        # Ignore words which match the input word (when converted to uppercase)
        # Ignore words which exist in uppercase in the uppMatches array.
        
        upperCandidate =  candidate.upper()
        if  upperCandidate == upperWord or upperCandidate in upperMatches:
            continue

        sortedCandidate = "".join( sorted(upperCandidate))
        if sortedCandidate == sortedWord:
            upperMatches.append(sortedCandidate)
            list.append(candidate)

    return list;
