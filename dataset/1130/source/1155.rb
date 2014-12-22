def combine_anagrams(words)

  hash = Hash.new
  hash.default = []
  
  words.each do |word|
    hash[word.upcase.chars.sort.join] = hash[word.upcase.chars.sort.join]+[word]
  end
  
  return hash.values
  
  
  
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])