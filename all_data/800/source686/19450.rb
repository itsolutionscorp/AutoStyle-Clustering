def combine_anagrams(words)
  tmp_hash = Hash.new([])
  words.each {|word| tmp_hash[word.downcase.split(//).sort] += [word] }
  #words.each {|word| puts word.downcase.split(//).sort }
  #tmp_hash.each_pair {|a,b| puts a,b}
  tmp_hash.values
end

#puts combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
