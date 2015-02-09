def combine_anagrams(words)
anahash = {}
temp = ""
words.each { |wd| 
  temp = wd.downcase.split(//).sort.join
  anahash[temp] = [] 
  }
words.each { |wd| 
  temp = wd.downcase.split(//).sort.join
  anahash[temp].push(wd) 
  }
return anahash.values
end
