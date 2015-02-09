def sorted_string(s)
  s.split(//).sort.join
end

def combine_anagrams(words)
  groups = {}
  words.each do |w|
    groupkey = sorted_string(w.downcase)
    groups[groupkey] = (groups[groupkey] || []) << w
  end
  groups.values
end

# inputwords = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# puts combine_anagrams(inputwords)