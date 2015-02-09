def combine_anagrams(words)
	temp = words.map{|x| x.downcase}
	res=[]
	comp=[]
	temp.each_with_index{|x,y|
		added=false
		comp.each_with_index{|rx,ry|
			ra=rx.split("").sort
			rt=x.split("").sort
			if ra==rt
				res[ry] << words[y]
				added=true
			end
		}
		if !added
			newRes = []
			newRes << words[y]
			res << newRes
			comp << x
		end
	}
	return res
end