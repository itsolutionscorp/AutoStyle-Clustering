def combine_anagrams(words)
	words_cp = Array.new(0)
	words.each do |str|
		words_cp << str.downcase.chars.sort.join
	end
	words_cp.sort!

	anagrams = Array.new(0)
	while words_cp[0] != nil
		anagram = Array.new(0)
		words.each do |word|
			if words_cp[0] == word.downcase.chars.sort.join
				#put to output array
				anagram << word
			end
		end 
		anagrams << anagram
		#remove previous word
		words_cp.delete(words_cp[0])
	end			#loop the while untill words_cp contains no entry
	anagrams
end
