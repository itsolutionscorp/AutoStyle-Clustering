def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    anagram = word.downcase.chars.sort.join
    unless anagrams[anagram]
      anagrams[anagram] = Array.new
    end
    anagrams[anagram] << word
	end
	anagrams.values
end

puts combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']