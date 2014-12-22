def combine_anagrams(words)
  wordHash = Hash.new([])
  words.each {|word| wordHash[word.downcase.split(//).sort.join] += [word]}
  return wordHash.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
