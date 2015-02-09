def combine_anagrams(words)
  combined = Hash.new{|hash, key| hash[key] = Array.new;}
  words.each {|word| combined[word.downcase.chars.sort.join] << word}
  combined.values
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
