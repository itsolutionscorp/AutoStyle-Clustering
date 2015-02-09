def combine_anagrams(words)
	allCounts = Array.new()
	
	words.each do |word|
		# do the counting and return an array with counts
		counts = Hash.new()
		word.downcase.each_char do |char|
			if (counts.has_key?(char))
				counts[char] = counts[char] + 1
			else 
				counts[char] = 1
			end
		end
		#puts word+" "+ counts.to_s
		
		#check if we had a word with such a count previously
		found = false
		allCounts.each do |checkCount|
			if checkCount[0] == counts
				checkCount[1] << word
				found = true
			end
		end
		if !found 
			allCounts << [counts, [word]]
		end
	end
	out = Array.new
	allCounts.each do |entry|
		out << entry[1]
	end
	return out
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect