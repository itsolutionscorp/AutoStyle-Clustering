def combine_anagrams(words)
	anagram_words = words.map {|w| w.downcase.chars.sort.join}
	sorts = anagram_words.uniq
	groups = Array.new(sorts.length) {Array.new}
	words.each_index do |i|
		groups[sorts.index(anagram_words[i])] << words[i]
	end
	return groups
end
=begin
testcase = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(testcase).inspect
=end