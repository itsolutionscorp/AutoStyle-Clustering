def combine_anagrams(words)
	result = Hash.new
	words.each do |word|
		ordered_word = word.downcase.chars.sort.join
		flag = false
		result.each_key do |key| 
			if key == ordered_word
				result[key].push word
				flag = true
				break
			end
		end
		result[ordered_word] = Array.new.push(word) if not flag
	end
	
	return result.values
end