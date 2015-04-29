def detect_anagrams(word, anagram_list):
    return  [x for x in anagram_list if sorted(word.lower()) == sorted(x.lower()) and not word.lower() == x.lower()]
