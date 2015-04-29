# (a)

def combine_anagrams(words)
  
  anagrams = Hash.new()
  
  words.each do |word|
    
    anagram = word.downcase.split(//).sort.join
    
    if anagrams.has_key?(anagram) then
      anagrams[anagram].insert(-1, word)
    else
      anagrams[anagram] = [word]
    end
    
  end
  
  return anagrams.values
  
end

# combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
