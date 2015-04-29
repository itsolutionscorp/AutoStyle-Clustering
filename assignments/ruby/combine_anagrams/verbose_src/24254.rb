def combine_anagrams(words)
   h = words.inject(Hash.new []) do |hash, word|
     hash[word.downcase.chars.sort] += [word]
     hash
   end
  
   h.values
end
