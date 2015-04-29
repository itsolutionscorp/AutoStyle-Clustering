def combine_anagrams(words)
  anagrams = Array.new(0)
  words.map{|word| anagrams << words.select{|w| w.downcase.chars.sort.join == word.downcase.chars.sort.join}}
  anagrams.uniq
end

#puts combine_anagrams(['cars','for','potatoes','racs','four','scar','creams','scream'])
