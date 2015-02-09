def combine_anagrams(words)
	
	anagrams = Hash.new
	
	words.each do |word| 
		key = word.downcase.split(//).sort.join
		anagram = anagrams[key]
				
		if (anagram == nil) 
			anagram = Array.new
			anagrams[key] = anagram 	
		end
		
		anagram.push(word)
	end
	
	anagrams.values
end