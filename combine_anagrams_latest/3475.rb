def combine_anagrams words
  anagrams = Hash.new { |hash, key| hash[key] = [] }
  
  words.each do |word|
    anagrams[word.downcase.split(//).sort] << word
  end
  
  anagrams.values
end

# puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]