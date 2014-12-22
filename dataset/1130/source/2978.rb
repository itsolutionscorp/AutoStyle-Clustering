def combine_anagrams(words)
  anagrams = {}
  words.each { |w|
    key =  w.downcase.split(%r//).sort.join
    if not anagrams.has_key?(key)
      anagrams[key] = []
    end
    anagrams[key] << w
  }
  anagrams.values
end

the_words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
print combine_anagrams(the_words)