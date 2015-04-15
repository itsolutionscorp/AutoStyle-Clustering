def detect_anagrams(target,list_of_words):
    return [word for word in list_of_words if sorted(word.lower())==sorted(target.lower()) and word.lower()!=target.lower()]
