def detect_anagrams(word1,wordlist):
    '''
    Return True if anagram of word1 found in wordList else False
    :param word1: String
    :param wordlist: List of Strings
    :return: bool
    '''
    word1_lower = sorted(word1.lower())
    return [word for word in wordlist if sorted(word.lower()) == word1_lower and word.lower() != word1.lower()]
#print (detect_anagrams('ant', 'tan stand at'.split()))
#print (detect_anagrams('banana', ['banana']))
