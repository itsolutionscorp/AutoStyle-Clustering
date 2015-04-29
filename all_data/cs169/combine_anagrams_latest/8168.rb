def combine_anagrams(words)
   anagramHash = Hash.new()

   words.each do |word|
      anagram = word.downcase.chars.sort.join
      anagramHash[anagram].nil? ? anagramHash[anagram] = Array.new([word]) : anagramHash[anagram] << word
   end  

   anagramHash.values
end

p combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )
