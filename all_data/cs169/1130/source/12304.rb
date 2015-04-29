def combine_anagrams(words)
	result = {}
	
	words.each do |word|
		key = word.downcase.split('').sort.join
		result[key] ||= []
		result[key] << word
	end
	
	result.values
	# words.group_by {
		# |word| word.chars.sort 
	# }.values
end