def combine_anagrams(words)
	map = Hash.new;
	words.each do |word|
		key = word.downcase().chars.sort.join
		if map.has_key?(key)
			values = map[key].flatten
			values[values.count] = word
			map[key] = values
		else
			value = Array.new
			value[0] = word
			map[key] = value
		end		
	end

	output = Array.new
	map.keys.each do |key|
		output.push(Array.new(map[key]))
	end

	return output
end
