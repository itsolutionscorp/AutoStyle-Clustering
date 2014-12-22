def is_anagram(w1, w2) 
	if (w1.downcase.chars.sort.join == w2.downcase.chars.sort.join)
		return true
	else
		return false;
	end
end 


def get_index(words, word)
	return nil unless words.length > 0
	i = 0
	words.each do |w|
		if w[0] != nil
			if is_anagram(w[0], word)
				return i;
			end
		end
		i += 1;
	end
	return nil
end

def combine_anagrams(words) 
	res = Array.new
	words.each do |word|
		i = get_index(res, word)
		#puts i
		#puts word
		if (i == nil)
			res << (Array.new << word) 
		else
			res[i] << word
		end
	end
	puts res.inspect
	return res
end 

puts combine_anagrams (['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])