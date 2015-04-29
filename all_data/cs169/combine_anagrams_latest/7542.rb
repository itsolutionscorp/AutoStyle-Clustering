def combine_anagrams(words)
		unique_letter_sequence_hash = {}
		anagrams_hash = []
		words.each do |word|
			if unique_letter_sequence_hash[word.downcase.chars.sort.join] == nil
				unique_letter_sequence_hash[word.downcase.chars.sort.join] = unique_letter_sequence_hash.length
			end
			if anagrams_hash[unique_letter_sequence_hash[word.downcase.chars.sort.join]] == nil
				anagrams_hash[unique_letter_sequence_hash[word.downcase.chars.sort.join]] = [word]
			else
				anagrams_hash[unique_letter_sequence_hash[word.downcase.chars.sort.join]] << word
			end
			
		end
		return anagrams_hash
end

print combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts "\n"