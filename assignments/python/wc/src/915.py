import re 
def word_count(text):
  histogram = {} 
  whitespace = '\s+'
  words = re.split(whitespace, text) 
  for word in words: 
    if word in histogram:   
      histogram[word] += 1 
    else:  
      histogram[word] = 1 
  return histogram
