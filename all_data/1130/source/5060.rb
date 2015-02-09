def combine_anagrams(word)
	a=Hash.new
	word.each{|item|
		index =  item.downcase.split(//).sort.to_s
		if a.has_key?(index)
			a[index] = a[index] << item
		else
			a[index] = [item]
		end
	}
	return a.values
end