def combine_anagrams(words)
	decons = []
	toReturn = []
	for w in words
		da = []
		w.downcase.chars{ |x| da+=[x] }
		if da.length > 1 then da.sort! end
		#puts da.join(',')
		if decons.index(da)
			toReturn[decons.index(da)] += [w]
		else
			decons += [da]
			toReturn += [[w]]
		end
		#puts decons.join('-')
	end	
	return toReturn
end
