def word_count(message):
   wordDict = {}
   message = message.lstrip()
   message = message.rstrip()
   wordsList = message.split()
   for word in wordsList:
      wordDict[word] = wordsList.count(word)   
   return wordDict
