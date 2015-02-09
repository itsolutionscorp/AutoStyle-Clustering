# potrzeba zmienic wszystkie slowa n amale litery
def combine_anagrams(words)
	anagrams = words.group_by { |word| word.downcase.chars.sort }.values
	return anagrams
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'] )

