def combine_anagrams(words)
	anagram_table = Hash::new
	words.each{|word|
		key = word.downcase.unpack('C*').sort.pack('C*')
		if anagram_table.key?(key) then
			anagram_table[key] = anagram_table[key].push(word)
		else
			anagram_table[key] = [word]
		end
	}
	anagram_table.values
end