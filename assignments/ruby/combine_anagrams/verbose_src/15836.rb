words1 = Array['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

words2 = Array['A', 'for', 'potatoes', 'racs', 'four','a', 'creams', 'scream']

def combine_anagrams(words)
  return words.collect {|word| words.select {|x| x.downcase.chars.sort{ |a, b| a.casecmp(b) }.join == word.downcase.chars.sort { |a, b| a.casecmp(b) }.join }}.uniq
end

p combine_anagrams(words1)
p combine_anagrams(words2)
