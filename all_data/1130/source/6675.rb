def combine_anagrams(words)

	if words.length == 0
		return []
	end

	index = -1
	letters_sorted = words.map { |w| [w.downcase.split('').
                                          sort.join, index += 1] }
	words_sorted = letters_sorted.sort { |x, y| x[0] <=> y[0] }
	
	match = ""
	in_index = -1
	out_index = -1
	result = [[]]
        words_sorted.each do |a| 
		if match != a[0]
			out_index += 1
			match = a[0]
			in_index = -1
			result[out_index] = []
		end
		in_index += 1
		result[out_index][in_index] = words[a[1]]
	end

	result	# return the result
end
