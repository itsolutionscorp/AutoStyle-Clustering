def combine_anagrams(words)
  words.group_by { |word| word.downcase.split(//).sort.to_s}.values
end

#tests
#p3 = Part3.new()
#puts p3.combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
