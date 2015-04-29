def combine_anagrams(words)
	groups = Hash.new
	words.each do |word|
   if groups[word.downcase.chars.sort.join]
			groups[word.downcase.chars.sort.join].push(word)
		else
			groups[word.downcase.chars.sort.join] = [word]
		end
	end

	anagrams = Array.new
	groups.each_value { |value| anagrams.push(value) }

	return anagrams
end
