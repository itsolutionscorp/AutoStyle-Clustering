
#original_words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#original_words2 = ['cArs', 'for', 'PoTatoes', 'raCs', 'four','scar', 'creAms', 'scream']

class String
	def is_anagram_of?(new_string)
		return new_string.downcase.chars.sort == self.downcase.chars.sort
	end
end


def combine_anagrams(words)
	#take the first word and cycle through each other word

	#Chack if it is an anagram, if so add it to an array of anagrams (and add that to the nums to skip array)

	#then check the next word (as long as it shouldn't be skipped

	ones_to_skip = []
	found_words = []
	
	for i in 0..words.length-1 
		unless ones_to_skip.include?(i) then
			#puts "Checking word #{words[i]}"
			found_words_iteration = []
			for j in 0..words.length-1
				unless ones_to_skip.include?(j) then
					#checks if it is an anagram (should always execute once as the word will be an anagram of itself)
					if words[i].is_anagram_of?(words[j])				
						#puts "#{words[i]} is an anagram of #{words[j]}" 
						ones_to_skip << j						
						found_words_iteration << words[j]							
					end
				end
			end
			#add in the found words for this iteration to the overall found_words array			
			found_words << found_words_iteration
		end	
	end

	return found_words
end


#puts "Found words = #{combine_anagrams(original_words2)}"
