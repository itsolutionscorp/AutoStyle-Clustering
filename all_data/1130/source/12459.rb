def combine_anagrams( words )
	anagrams = [ ]
	words.each do |word|
		added = false
		anagrams.each do |anagram|
			puts 'Check if ' + word + ' = ' + anagram[0]
			if word.downcase.chars.sort.join == anagram[ 0 ].downcase.chars.sort.join
				puts 'Match'
				anagram.push( word )
				added = true
				break
			end
		end
		if !added
			anagrams.push( [ word ] )
		end
	end
	return anagrams
end

puts combine_anagrams( [ 'Cars', 'for', 'potatoes', 'racs', 'four', 'scar' ] ).inspect