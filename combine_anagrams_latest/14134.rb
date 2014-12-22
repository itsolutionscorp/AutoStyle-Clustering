# Part 3: anagrams

def combine_anagrams(words)
  (words.group_by {|word| word.chars.sort.join.downcase}).values
end

# puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
