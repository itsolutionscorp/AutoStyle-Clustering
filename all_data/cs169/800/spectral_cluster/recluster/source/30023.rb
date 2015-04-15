def combine_anagrams(words)
	anagrams = {}
	words.each do |word|
		holder = []
		word.each_char { |char| holder << char.downcase }
		holder.sort!

		if anagrams.has_key?(holder.join)
			anagrams[holder.join] << word
		else
			anagrams[holder.join] = [word]
		end
	end

	return anagrams.values
end