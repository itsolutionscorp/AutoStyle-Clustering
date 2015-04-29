def combine_anagrams(words)
 
	combined_list = []

	words.each do |word|

		sorted = word.chars.sort_by(&:downcase).join
		inserted = false

		combined_list.each do |anagram|

			compare1 = sorted.downcase
			compare2 = anagram[0].chars.sort_by(&:downcase).join.downcase

			puts compare1 + ' ' + compare2
			puts " " 

			if compare1 == compare2
				anagram << word
				inserted = true	
			end
		end	


		if inserted == false
			combined_list << [word]
		end	
	end


	return combined_list

end


