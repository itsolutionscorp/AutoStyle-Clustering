def combine_anagrams(words)

  hash = Hash.new

  words.each do|word|
    
    if not hash.has_key?(word.downcase.split("").sort.join(""))
      hash[word.downcase.split("").sort.join("")] = []
    end
    
    hash[word.downcase.split("").sort.join("")].push(word)
    
  end
  
  array = []
  
  hash.each do|key,value|
  
    array.push(value)
    
  end

  return array

end

print combine_anagrams(['Cars', 'for', 'potatoes', 'racS', 'four','scar', 'creams',
'scream', 'a', 'a', 'A'])
