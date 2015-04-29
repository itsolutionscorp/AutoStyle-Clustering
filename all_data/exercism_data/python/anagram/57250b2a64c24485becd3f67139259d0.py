from collections import Counter

def _isAnagram(givenWord, word):
  if givenWord.lower() == word.lower():
    return False
  return Counter(givenWord.lower()) == Counter(word.lower())

def detect_anagrams(givenWord, words):
  return [word for word in words if _isAnagram(givenWord, word)]
