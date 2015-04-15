

def detect_anagrams(word,wordlist):

  return [wd for wd in wordlist if sorted(str.lower(word)) == sorted(str.lower(wd)) 
        and str.lower(wd) != str.lower(word)]
  
