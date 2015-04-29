def detect_anagrams(word, possible_anagrams):
    anagrams = [anagram for anagram in possible_anagrams 
    if sorted(anagram.lower()) == sorted(word.lower())      
    and anagram.lower() != word.lower()]
    return anagrams
