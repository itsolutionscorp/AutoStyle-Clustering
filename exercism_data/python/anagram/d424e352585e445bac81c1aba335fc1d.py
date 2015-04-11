def detect_anagrams(string, wordlist):
    sort = sort_word(string.lower())
    return [w for w in wordlist if is_anagram(string, w)]


def sort_word(w):
    return ''.join(sorted(w))

def is_anagram(word, test):
    slt = sort_word(test.lower())
    slw = sort_word(word.lower())
    return slt==slw and test.lower() != word.lower()
