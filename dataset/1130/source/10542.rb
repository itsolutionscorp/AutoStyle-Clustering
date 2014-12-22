def combine_anagrams(words)
	result = words.collect do |word|
		word.downcase.each_char.sort.join
	end
	positions = Hash.new {|h, k| h[k] = []}
	retval = []
	result.each_with_index do |word, i|
		positions[word].push i
	end
	retval = positions.values.collect do |a|
		a.collect! { |val| val = words[val] }
	end
end
