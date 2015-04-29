def combine_anagrams(words)	
	#words = %w[cars scar for four creams scream racs]
	anagrams = words.group_by { |word| word.chars.sort }.values
	# => [["cars", "scar", "racs"], ["for"], ["four"], ["creams", "scream"]]
end
