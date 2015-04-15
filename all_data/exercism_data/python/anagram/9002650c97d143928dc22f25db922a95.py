def isAnagram(word, listReference):
  listWord = list(word)
  listWord.sort()
  return listWord == listReference

def detect_anagrams(word, dictionary):
  reference = word.lower()
  listReference = list(reference)
  listReference.sort()

  result = []

  for otherWord in dictionary:
    testWord = otherWord.lower()
    if testWord != reference and isAnagram(testWord, listReference):
      result.append(otherWord)

  return result
