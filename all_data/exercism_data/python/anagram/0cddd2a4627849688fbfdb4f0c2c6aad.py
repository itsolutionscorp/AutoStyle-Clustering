def check_anagram(word1, word2):
    '''Check if two words are an anagram (Case insensitive)

    Args:
        word1 (string):
        word2 (string):

    Returns:
        bool:
            False if words are the same or not an anagram
            True if words are an anagram (Case insensitive)
    '''
    word1 = list(word1.lower())
    word2 = list(word2.lower())
    if word1 == word2:
        return False
    word1.sort()
    word2.sort()
    return word1 == word2


def detect_anagrams(check_word, wordlist):
    '''Detect if any words from wordlist is an anagram of check_word

    Args:
        check_word (string):
        wordlist (list (of strings)):

    Returns:
        list: A list of all the anagram words
    '''
    return [word for word in wordlist if check_anagram(check_word, word)]
