def combine_anagrams(words)
	a=[]
	words.each {|m| a << m.downcase.chars.sort.join}
	a.uniq!
	puts a
	res = []
	a.each do |w|
		grp = []
		words.each do |k| 
			if k.downcase.chars.sort.join == w then
				grp << k
			end
		end
		res << grp
	end
	return res
end