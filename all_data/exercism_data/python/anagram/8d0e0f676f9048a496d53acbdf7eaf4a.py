#
# captainnurple's code for the Python Anagram exercise.
#
'''
Method detect_anagrams takes a keyword and a list of comparison words
and returns a comprehensive list containing any anagram matches
'''
def detect_anagrams(keyword, compWords):
    resultList = [];
    keyword = keyword.lower() # input cleaning

    for word in compWords:
        lcWord = word.lower() #input cleaning

        # sanity check
        if (len(lcWord) != len(keyword)) or (lcWord == keyword): # Same length; not same word
            continue

        # Now we'll check each letter for occurrence in keyword
        # Only if every occurs is it an anagram
        matchNum = 0
        keyComp = list(keyword) # We need clean mutable copy for comps
        for letter in lcWord:
            if letter in keyComp:
                matchNum += 1
                keyComp.remove(letter) # Prevents false positives like 'galea'/'eagle'

        # If we filled matchNum store the anagram
        if matchNum == len(keyword):
            resultList.append(word) # Back to word not lcWord so we return identical to inputs

    return resultList
