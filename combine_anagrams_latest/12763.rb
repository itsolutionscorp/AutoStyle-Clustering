def combine_anagrams(words)
	anagrams = {} # to map anagram hashes to group keys
	groups = []
	words.each do |w|
		ana_key = w.downcase.split('').sort.join
		if (anagrams.has_key?(ana_key))
			# there has to be a better way to do this...
		else
			anagrams[ana_key] = groups.length
		end
		group_key = anagrams[ana_key]

		if (groups[group_key])
			groups[group_key] << w
		else
			groups[group_key] = [w]
		end
	end
	groups
end

