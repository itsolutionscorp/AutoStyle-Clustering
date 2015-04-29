def are_anagrams(s1, s2)
	s1.upcase.chars.sort.join == s2.upcase.chars.sort.join
end

def combine_anagrams(words_)
	words = words_.dup
	res = []
	for i in 0..(words.length-1)
		if words[i]=='' then next end
		group = [words[i]]
		for j in i+1..(words.length-1)
			if are_anagrams(words[i], words[j])
				group << words[j]
				words[j] = '';
			end
		end
		res << group
	end
	res
end
