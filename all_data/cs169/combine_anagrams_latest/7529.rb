def combine_anagrams(words)
	out_words = Array.new(0)
	words.each do |wrd| 
  		temp = []
		words.each { |wrd2| temp.push(wrd2) if ( wrd2.downcase.split(//).sort == wrd.downcase.split(//).sort ) }
		out_words.push(temp)
	end
	return out_words.uniq
end
