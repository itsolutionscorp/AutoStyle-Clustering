"""
anagram.py: program that, given a word and a list of possible anagrams,
selects the correct sublist.
"""
def detect_anagrams(word, possibilities):
    """
    Function to return a list of anagram matches for incoming word.
        word: word to try and match against possibilities list
        possibilities: a list of words to match against for possible anagrams
    """
    # alphabetize the incoming word for matching
    reordered_word = ''.join(sorted(word.lower()))
    matches = []
    for match in possibilities:
        # check against case differences
        if word.lower() == match.lower():
            pass
        # try to match alphabetized word against alphabetized matches in list
        elif reordered_word == ''.join(sorted(match.lower())):
            matches.append(match)
    return matches
