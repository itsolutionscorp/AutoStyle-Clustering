def combine_anagrams(words)
	hash = {}
	groups = []
	words.each do |word|
		rearrange = word.downcase.split('').sort.join('')
		if hash[rearrange] != nil then
			hash[rearrange].push word
		else
			hash[rearrange] = []
			hash[rearrange].push word
		end
	end
	hash.each do |key, value|
		groups.push value
	end
	return groups
end