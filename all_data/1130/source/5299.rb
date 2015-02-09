

def combine_anagrams(words)
  words.group_by{|s| s.downcase.chars.sort.to_s}.values
end

p combine_anagrams(
['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
)

