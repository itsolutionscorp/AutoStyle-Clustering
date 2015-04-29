def word_count(phrase):
  
  wordmap = dict()
  
  for word in phrase.replace("\n", " ").split(" "):
  
    if word.strip() != "":
    
      if word in wordmap:
      
        wordmap[word] = wordmap[word] + 1
      
      else:
        wordmap[word] = 1
  
  return wordmap
  
  
  
