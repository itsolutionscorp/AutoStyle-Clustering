def combine_anagrams(words)
	l=[]
	words.each do |a|
		l << words.select {|b| a.downcase.chars.sort.join == b.downcase.chars.sort.join}
	end
	return l.uniq
end


