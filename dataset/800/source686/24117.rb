def combine_anagrams(words)
	anagrams = words.group_by { |word| word.downcase.chars.sort }.values
end

#tests
# words = ['caRs', 'For', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

# puts combine_anagrams(words).inspect