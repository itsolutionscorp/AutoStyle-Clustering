def combine_anagrams(words)
  groups = {}
  words.each do |word|
    base = word.downcase.split(//).sort().join()
    if !groups[base]
        groups[base] = []
    end
    groups[base] << word
  end
  return groups.each_value.to_a
end

a= ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#p combine_anagrams(a)
