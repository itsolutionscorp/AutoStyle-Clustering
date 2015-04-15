def is_anagram_of(a, b):
  a = a.lower()
  b = b.lower()

  if a == b:
    return False
  else:
    A = list(a)
    B = list(b)
    A.sort()
    B.sort()
    return A == B

def detect_anagrams(word, word_list):
  return [test_word for test_word in word_list if is_anagram_of(test_word, word)]
