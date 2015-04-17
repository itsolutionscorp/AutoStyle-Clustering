def detect_anagrams(word, dictionary):
  reference = word.lower()
  result = []
  for otherWord in dictionary:
    testWord = otherWord.lower()
    if testWord != reference and sorted(testWord) == sorted(reference):
      result.append(otherWord)
  return result
