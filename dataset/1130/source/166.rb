def combine_anagrams(words)
	output = []
	m = {}
	count = -1
	
	words.each do |w|
		ws = ""
		ws << w.downcase.each_char.sort.to_s
		#print ws + "\n"
		if m[ws] != nil
			output[m[ws]] << w
		else
			#print "Init " + ws + "\n"
			count += 1
			m[ws] = count
			output[m[ws]] = [w]
		end
	end
	return output
end