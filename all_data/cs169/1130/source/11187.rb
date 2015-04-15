 
def combine_anagrams(words)

  hash = Hash.new([])
  words.each do |word|

    hash[word.downcase.chars.sort.join] += [word] 
  end
  
  combined = []
  index = 0
  
  hash.each do |key,value|
    combined[index]= value
    index += 1
  end
  
  return combined

end
