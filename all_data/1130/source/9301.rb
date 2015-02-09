def combine_anagrams(words)
	h = Hash.new
	words.each do |word|
	 key = word.downcase.chars.sort.join
	 if h.has_key?(key)
	   h[key] << word
	 else
	   h[key] = [word]
	 end
	end
	return h.values
end

input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

puts combine_anagrams(input)