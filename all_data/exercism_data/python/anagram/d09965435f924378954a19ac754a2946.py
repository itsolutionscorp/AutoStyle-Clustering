def detect_anagrams(word,list_of_words):
    return [ x for x in list_of_words if sorted(x.lower()) == sorted(word.lower()) and x.lower() != word.lower()]
