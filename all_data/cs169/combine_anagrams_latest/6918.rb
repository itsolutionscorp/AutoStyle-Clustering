def combine_anagrams(words)
       
  sorted = []
  words.each {|word| sorted.push word.to_s.downcase.chars.sort.join}
  
  words_types = {}

  sorted.each_index do |x|
    if words_types.has_key? sorted[x]
      words_types[sorted[x]] = words_types[sorted[x]].push words[x]
    else
      words_types.store sorted[x], [words[x]]
    end
  end
  
  words_types.values

end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
