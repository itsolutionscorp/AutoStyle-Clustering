def detect_anagrams(word, wordlist):
  x = []
  for j in wordlist:
    if is_anagram(word.lower(), j.lower()):
      x.append(j)
  return x

def is_anagram(word1, word2):
  if len(word1) != len(word2) or (word1 == word2):
    return False

  list1 = list(word1)
  list2 = list(word2)

  list1.sort()
  list2.sort()

  pos = 0
  matches = True
  while pos < len(list1) and matches:
    if list1[pos] == list2[pos]:
      pos += 1
    else:
      matches = False
  return matches
