def combine_anagrams(words)
	words.group_by {|word| word.each_char.inject(Hash.new(0)) {|h, char| h[char.downcase] += 1; h}}.values
end
