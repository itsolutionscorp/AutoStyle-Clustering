# Part 3

def combine_anagrams(words)
  words.group_by { |w| w.downcase.chars.sort.join }.values
end

#puts combine_anagrams(['cArs', 'for', 'potatoes', 'raCs', 'four','Scar', 'creams', 'scream'] ).to_s