def combine_anagrams(words)
	wordshashes = Hash.new
	words.each do |w| 
		h = w.downcase.chars.to_a.sort.to_s
		if wordshashes[h] != nil
			wordshashes[h].insert(-1,w)
		else
			wordshashes[h] = [w]
		end
	end
	wi = []
	wordshashes.each_value do |i| 
		wi << i
	end;
	wi
end