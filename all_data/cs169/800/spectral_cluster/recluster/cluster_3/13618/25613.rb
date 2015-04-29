# software engineering for software as a service
# matthew mihok
# m.mihok@zerotwomedia.com

# PART 3
# Given an array of strings, write a method that groups them into anagram groups and returns 
# the array of groups.

def combine_anagrams(words) 
	result = []
	len = words.length
	for i in 0..len - 1
		compare_word = words[i].downcase.chars.sort.join
		current_arr = []
		words.each do |word|
			buffer = word.downcase.chars.sort.join
			if buffer.length == compare_word.length 
				if compare_word.start_with?(buffer)
					puts compare_word + " starts with " + buffer
					current_arr.push(word)
				end
			end
		end
		if(current_arr.length > 0 && result.index(current_arr) == nil)
			result.push(current_arr)
		end
		puts current_arr.to_s
	end
	return result
end

# Examples
puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s