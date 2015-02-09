def combine_anagrams(words)
	out=Array.new	
	s_words=words.map {|w| w.downcase.split(//).sort.join}
	groups=s_words.uniq
	0.upto(groups.length-1) { |i| 
	out<<words.select {|w| 
	w.downcase.split(//).sort.join=~Regexp.new('^'+groups[i]+'$')
	}
	}
return out
end
