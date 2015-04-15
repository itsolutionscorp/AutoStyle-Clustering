# HW-1 Part-3

def combine_anagrams(words)

	res = Array.new

	words.each { |word| 
		word_sorted = word.downcase.chars.sort.join;
		(i = res.assoc ( word_sorted )) ? i << word : res << [word_sorted, word]; 
	}

	#remove the sorted word, first element of each sub array
	res.each { |item| item.delete_at(0) }

	res
end

