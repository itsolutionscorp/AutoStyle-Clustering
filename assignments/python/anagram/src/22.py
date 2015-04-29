def detect_anagrams(word, phrase):
    '''
    return a list of words in phrase that are an anagram of word
    '''
    return [s for s in phrase if sorted(word.lower()) == sorted(s.lower())
            and word.lower() != s.lower()]
