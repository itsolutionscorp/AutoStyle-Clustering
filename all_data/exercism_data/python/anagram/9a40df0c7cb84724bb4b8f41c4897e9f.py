from itertools import permutations

def remove_dupes(ls):  
# was trying to do this with return_words as a dict 
# and list(set(return_words.values())) 
#but the list was getting out of order due to set()
  newls = []  
  for le in range(len(ls)):  
    if ls[le] not in ls[le+1:]:
      newls.append(ls[le])
      
  return newls 

def detect_anagrams(word,wordlist):
 
  return_words = [] 
  
  for wrd in wordlist:  
       
    for wd in permutations(word,len(word)):
      wd = ''.join(wd)     
      if str.lower(wrd) == str.lower(wd) and str.lower(wrd) != str.lower(word):  
        return_words.append(wrd)    
       
    
    return_words = remove_dupes(return_words)
  
  return return_words
