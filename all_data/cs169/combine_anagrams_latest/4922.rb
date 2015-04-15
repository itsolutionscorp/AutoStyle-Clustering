def combine_anagrams(words)
  groups = {}
  words.each do |word|
    normalized = word.downcase.chars.sort.join
    groups[normalized] ||= []
    groups[normalized] << word


  end
  return groups.values
end

#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#p combine_anagrams(input)