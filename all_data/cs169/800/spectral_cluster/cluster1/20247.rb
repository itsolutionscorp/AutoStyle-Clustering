########################################################
## HW1 3

def combine_anagrams(words)
  res = {}  

  words.each do |word| 
  key=word.downcase.split('').sort.join 
  #res[key] ||= [] 
  if res[key] == nil
    res[key] = []
  end  
  res[key] << word 
  end
  
  return res.values
  
end