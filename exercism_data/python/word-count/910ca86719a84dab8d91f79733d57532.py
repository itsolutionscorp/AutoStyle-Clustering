def word_count(phrase):
  wd_dict = {}
  words = phrase.split()
  for wd in words:
    if (wd in wd_dict.keys()):
      wd_dict[wd] += 1
    else:
      wd_dict[wd] = 1
  return wd_dict
    
  
