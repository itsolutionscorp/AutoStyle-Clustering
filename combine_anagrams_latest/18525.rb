def combine_anagrams(words)
	output = Array.new
	words.each do |word|
		foundit = false
		if output.length > 0
			output.each do |group|
				if group[0].downcase.split("").sort == word.downcase.split("").sort
					group << word
					foundit = true
				end
			end
			if foundit == false
				output << [word]
			end
		else
			output << [word]
		end
	end
	output
end