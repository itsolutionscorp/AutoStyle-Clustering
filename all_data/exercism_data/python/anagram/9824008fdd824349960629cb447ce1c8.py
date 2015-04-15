def match_words(word1,word2):
    if word1.lower() != word2.lower() and ''.join(sorted(word1.lower())) == ''.join(sorted(word2.lower())):
        return True

def detect_anagrams(word,candidates):
    return [candidate for candidate in candidates if match_words(word, candidate)]
