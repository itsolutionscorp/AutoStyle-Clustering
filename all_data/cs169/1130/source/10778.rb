def sort_string(string)
	tmp_str = string.clone
	tmp_str = tmp_str.downcase
	for j in 0..(tmp_str.length-2)
	for i in 0..(tmp_str.length-2)
		if (tmp_str[i] > tmp_str[i+1]) then 
			temp=tmp_str[i]
			tmp_str[i]=tmp_str[i+1]
			tmp_str[i+1]=temp
		end
	end
	end
	return tmp_str
end


def combine_anagrams(words)
	result = []
	words.each do |word|
		word_sorted = sort_string(word)
		inserted = false
		result.each do |elem|
			if sort_string(elem[0]) == word_sorted then 
				elem.push(word) 
				inserted = true
			end
		end
		if !inserted then result.push([word]) end
	end 
	return result
end