def combine_anagrams(words)
	available = [];list =[]
	a = []
	sort_words = []
	for i in 0..(words.length-1)
		sort_words.push(words[i].downcase.chars.sort.join)
		available[i] =true
	end

	for i in 0..(words.length-1)
		a.push(words[i]) if available[i]; available[i] = false
		for j in i+1..(words.length-1)
			 if (sort_words[i] == sort_words[j] && available[j]!=false)
				a<<words[j]
				available[j] = false
			end
		end
		list.push(a); a = []
	end

	list.reject{ |arr| arr.all? {|elem| elem.nil? || elem.strip.empty? }}
end

