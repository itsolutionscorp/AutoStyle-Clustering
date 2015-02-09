def combine_anagrams(words)
	aWrd = Hash.new
	words.each  { |wrd|
		wd = wrd.downcase.split(//)
		sd = wd.sort.join 
		if aWrd.has_key?(sd)
			aWrd[sd] << wrd
		else
			aWrd[sd] = [sd]
		end
	}
	outAry = []
	aWrd.each { |k,v| outAry << v }
	return outAry
end