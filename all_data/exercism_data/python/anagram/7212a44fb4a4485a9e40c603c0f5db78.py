# Anagram Python Exercism, 1st iteration
def is_anagram(word1, word2):
    return sorted(word1.lower()) == sorted(word2.lower()) and word1.lower() != word2.lower()
    
def detect_anagrams(word,lst): 
    return [anag for anag in lst if is_anagram(word,anag)]
