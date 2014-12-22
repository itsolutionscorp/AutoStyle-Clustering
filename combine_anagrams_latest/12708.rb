def combine_anagrams(words) 
  @words_hash = words.each_with_object(Hash.new []) do |word, anagrams|
    anagrams[word.downcase.chars.sort.join] += [word]
  end
  @words_hash.values.to_a
end



# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]] 
# HINT: you can quickly tell if two words are anagrams by sorting their 
# letters, keeping in mind that upper vs lowercase doesn't matter

# Testing
#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#combine_anagrams(words)
#words = ['Cars','For', 'potatoes', 'racs', 'four', 'Scar', 'creams', 'scream']
