#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

def combine_anagrams(words)
  words.group_by{|word| word.downcase.split(//).sort.join}.values
end

#p combine_anagrams(words)