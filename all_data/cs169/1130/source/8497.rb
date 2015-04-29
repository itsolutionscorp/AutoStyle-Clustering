

def combine_anagrams(words)
	
	hash = Hash.new
	words.each do |word|
		tmp = word.downcase.split('').sort.join
		if hash.has_key? tmp
			hash[tmp] << word
		else
			hash[tmp] = [word]
		end
	end
	
	output = []
	not_anagrams = []
	hash.keys.each do |k|
		output << hash[k]
	end
	p output
end

#puts combine_anagrams ['caRs', 'for', 'potatos', 'racs', 'four', 'scar', 'creams', 'scream']
#puts combine_anagrams ['c', 's']