# input:

#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]


def combine_anagrams(words)
	def are_anagrams?(word1, word2)
		# print "are_anagrams?(#{word1}, #{word2})\n"
		
		return word1.downcase.split('').sort == word2.downcase.split('').sort
	end

	groups = []
	words.each do |word|
		group = groups.find {|group| are_anagrams?(group[0],word) }
		if group
			group << word
		else
			groups << [word]
		end
	end
	return groups
end

# p combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
