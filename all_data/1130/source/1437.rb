def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
      group = word.downcase.split(//).sort.join
      if !anagrams.has_key? group
        anagrams[group] = []
      end
      anagrams[group] << word
    end
  anagrams.values
end

# puts combine_anagrams ['CARS', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
