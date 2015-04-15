def combine_anagrams(words)
	anagrams = []
	i = 0
	indices = []
	match = []

	while words.length > 0
		anagrams.push([words.shift])
		anagram = anagrams[i].first.downcase.split("").sort.join("")
		puts anagram
		words.each_with_index do |w, idx|
			if w.downcase.split("").sort.join("") == anagram
				match << w
				anagrams[i] += match
			end
		end
		anagrams[i].flatten
		anagrams[i].uniq!
		words.delete(match.shift) while match.length > 0
		i += 1
	end
	anagrams
end

arr = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
arr2 = combine_anagrams(arr)
puts "#{arr2}"
