def combine_anagrams(words)
	roots = []
	output = []

	words.each do |word|
		puts 'Analizing: ' + word

		root = word.downcase.split('').sort.join('')
		puts 'Root: ' + root

		index = roots.index(root)
		if !index
			puts 'Adding new root: ' + root
			roots << root
			index = roots.size - 1
		end

		if !output[index]
			output[index] = []
		end

		output[index] << word
	end

	puts 'Anagrams found: ' + output.to_s
	output
end

# input:
combine_anagrams ['Cars', 'for', 'potatoes', 'Racs', 'four','Scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter