def combine_anagrams(words)
	words.inject( Hash.new { []}) { | hash, word|
		chars = word.downcase.chars.sort.join
		hash[ chars] = hash[ chars] << word
		hash
	}.values
end

# test cases
=begin
print combine_anagrams([
	'cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']), "\n"
=end