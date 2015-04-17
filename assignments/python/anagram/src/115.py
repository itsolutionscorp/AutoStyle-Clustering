import string
def detect_anagrams(target,list_of_words):
    return [word for word in list_of_words if sorted(list(word.lower()))==sorted(list(target.lower())) and word.lower()!=target.lower()]
