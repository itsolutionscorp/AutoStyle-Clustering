def combine_anagrams(*words)
	words = words.flatten
	hash_table = Hash.new
	words.each do |x|
		x.split.each do |y|
			key = y.downcase.chars.sort.join
			if hash_table.has_key?(key)
				hash_table[key] += [y] if (hash_table.values_at(key).include?(y).! or (key.length == 1 and y.upcase != y))
#				hash_table[key] += [y] if (hash_table.values_at(key).flatten.include?(y).! or (key.length == 1 and y.upcase != y))
			else 
				hash_table[key] = [y]
			end
		end
	end
	return hash_table.values
end
print combine_anagrams(['cars', 'cars', 'racs', 'scar'].sort, ['four'], ['for'], ['potatoes'], ['creams', 'scream', 'scream'].sort), "\n"
#print combine_anagrams(['pots', 'spot', 'stop', 'tops', 'tops'].sort, ['spots', 'stops'].sort, ['sausage']), "\n"
#print combine_anagrams(["a", "a"].sort, ["b", "b"], ["c"], ["d"]), "\n"
#print combine_anagrams(['a', 'a', 'A', 'A', 'B'], ['b', 'b'], ['c'], ['d', 'B']), "\n"
#print combine_anagrams(["a", "a"], ["b", "b"], ["c"]), "\n"
#print combine_anagrams(['cars', 'cars', 'cars', 'for', 'For', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']), "\n"
#print combine_anagrams('cars', 'cars', 'cars', 'for', 'For', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'), "\n"
#print combine_anagrams(['cars', 'cars', 'racs', 'scar'].sort, ['four'], ['for'], ['potatoes'], ['creams', 'scream', 'scream'].sort), "\n"
