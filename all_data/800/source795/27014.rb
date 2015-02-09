def combine_anagrams(words)
	words.group_by { |word| word.downcase.chars.sort.join }.map { |key, value| value }
end

print combine_anagrams(['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']), "\n"

