def combine_anagrams (words)
	res = Array.new
	words.each do |word|
		sorted_word = word.downcase.chars.sort.join
	  
		if res.length > 0
			pr = 0
			res.each do |group|
				group_word = group[0].downcase.chars.sort.join
			  
				if sorted_word == group_word
					group.push(word)
			  		pr = 1
				end
			end

			if pr == 0
				res.push([word])
			end
	  	else			
	  		res[0] = [word]
	  	end
	  end

	  res
end