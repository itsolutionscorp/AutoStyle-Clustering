def combine_anagrams(words)
  hash_array = Hash.new(Array.new)
  words.each { |word|
    if hash_array[word.downcase.chars.sort].empty?
      hash_array[word.downcase.chars.sort] = Array.[](word)
    else
      hash_array[word.downcase.chars.sort] << word  
    end
  }
  return hash_array.each_value.to_a
end

p combine_anagrams(['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
