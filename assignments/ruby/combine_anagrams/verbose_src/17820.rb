def combine_anagrams(words)
	h = Array.new
	w2 = Array.new
	words.each{|x| w2 << x.downcase.split(//).sort.join}
	
	words.each_index{|x|
		if (not words[x].empty?)
			s = Array.new
			words.each_index{|y|
				if w2[x] == w2[y]
					s << words[y]
					words[y] = ''
				end
			}
			h << s
		end
	}
	return h
end


#~ words =  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams',
#~ 'scream']
#~ 
#~ w2 = words.each{|x| x.downcase.split(//).sort.join}
#~ words.each{|x| x.downcase.split(//).sort.join}.inspect
#~ puts  w2.inspect
#~ 
#~ puts combine_anagrams(words).inspect
