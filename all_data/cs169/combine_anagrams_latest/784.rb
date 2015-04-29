def combine_anagrams(words)
	groups = Hash.new([])
	words.each do |word|
		group_name = word.downcase.gsub(/\W/, "").chars.sort.join
		groups[group_name] = [word].concat(groups[group_name])
	end
	return groups.values
end
