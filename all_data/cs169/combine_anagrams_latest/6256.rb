def combine_anagrams(words)
  a = []
#  words.group_by { |w| w.downcase.chars.sort.join }
#  words.group_by { |w| w.downcase.chars.sort.join }.select { |w| a << w } 
  words.group_by { |w| w.downcase.chars.sort.join }.values
#  return a
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
