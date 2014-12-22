def combine_anagrams(words)
  p words.group_by{|w| w.downcase.split(//).sort.to_s}.values
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'];
combine_anagrams(words)
# p "cars".split(//)
