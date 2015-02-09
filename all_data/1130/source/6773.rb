def combine_anagrams(input_words)
 anagrams = Array.new
 words = input_words
 while(words.length != 0) 
	 anagram = words.at(0).chars.sort.join
	 temp_array = Array.new
	 words.each do |word|
		if word.chars.sort.join == anagram then 
		 temp_array.push(word)
		 words.delete(word)
		end 
	 end
	 anagrams.push(temp_array)
 end
 return anagrams
end
# input:
combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
