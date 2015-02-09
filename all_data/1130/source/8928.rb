def combine_anagrams(words)
	wcount=Hash.new(0)
	words.each do |word|
		wcount[word]=word.downcase.chars.sort.join
	end

	result=Array.new
	wcount.group_by {|key,value| value}.each do |element|
		inner=Array.new
		element[1].each do |pair|
			inner<<pair[0]
		end
		result<<inner
	end
	return result

end