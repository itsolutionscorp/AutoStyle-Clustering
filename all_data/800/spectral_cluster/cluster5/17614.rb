# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def anagram_form(word)
  word.chars.sort_by(&:downcase).join
end

def combine_anagrams(words)
  uniq_anagrams = []
  anagrams = []
  words.each { |word| uniq_anagrams.push( anagram_form(word) )}
  uniq_anagrams.uniq!
  
  uniq_anagrams.each { |anagram| anagrams.push( (words.select {|word| anagram_form(word) == anagram }).uniq )}
  anagrams.uniq
end


puts combine_anagrams(['cars', 'for', 'potatoes', 'Racs', 'racs', 'four', 'Four','scar', 'creams', 
'scream']).inspect
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
