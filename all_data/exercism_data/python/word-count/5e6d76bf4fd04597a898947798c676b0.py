def word_count(phrase):
  words = phrase.split() 
  wordDict = {}

  for word in words:
      if word in wordDict:
        wordDict[word] += 1
      else:
        wordDict[word] = 1

  return wordDict
