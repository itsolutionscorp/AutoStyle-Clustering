from collections import Counter
def detect_anagrams(word, words):
    l = len(word)
    c = Counter(word.lower())
    return [eachWord for eachWord in words\
                if len(eachWord) == l\
                    and Counter(eachWord.lower()) == c\
                    and word.lower() != eachWord.lower()]
