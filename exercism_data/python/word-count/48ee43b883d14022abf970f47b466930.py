def word_count(message):
# create a empty dictionary for returning the data   
   wordDict = {}
   
# remove preceding whitespace   
   message = message.lstrip()
# remove trailing whitespace   
   message = message.rstrip()
# create a list of each word in phrase   
   wordsList = message.split()
# iterate through list and create a dictionary of data   
   for word in wordsList:
      wordDict[word] = wordsList.count(word)   
      
   return wordDict
