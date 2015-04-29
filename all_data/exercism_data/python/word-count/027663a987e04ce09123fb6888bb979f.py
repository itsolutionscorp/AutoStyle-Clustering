def word_count(sentence): 
 
  allWords = sentence.split()
  uniqueList = list(set(sentence.split()))
 
  newList = {}
  for each in uniqueList:
    newList[each] = allWords.count(each)

  return newList 













 
