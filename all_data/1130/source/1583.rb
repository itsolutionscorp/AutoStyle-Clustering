def combine_anagrams(words)
  dict = {}
  dict.default = []
  words.each {|w| dict[w.downcase.split(//).sort] += [w];}
  return Array(dict.each_value)
end
#p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
