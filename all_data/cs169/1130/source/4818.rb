def combine_anagrams(words)
anagrams ={}
words.each { |w|
  key = w.downcase.chars.sort.join
  if(!anagrams.has_key?(key))
    anagrams[key] = []
  end
  anagrams[key] << w
}
return anagrams.values

end


puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
