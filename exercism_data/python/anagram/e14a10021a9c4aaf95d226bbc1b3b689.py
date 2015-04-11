def stod(word):
  wordD = {}
  for i in word.upper():
    if i in wordD:
      wordD[i] += 1
    else:
      wordD[i] = 1
  return wordD

def detect_anagrams(word, listL):
  wordD = stod(word)

  foundL = []

  for anagram in listL:
    if wordD == stod(anagram) and word.upper() != anagram.upper():
      foundL.append(anagram)
  return foundL
