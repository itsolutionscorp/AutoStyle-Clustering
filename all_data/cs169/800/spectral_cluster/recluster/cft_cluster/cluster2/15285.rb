def is_anagrams?(word1, word2)
	if (word1.length != word2.length)
		return false
	else
		word1 = word1.downcase
		word2 = word2.downcase
		h1 = Hash.new
		for i in 0..(word1.length - 1)
			ch = word1[i]
			if (h1[ch].nil?)
				h1[ch] = 1
			else
				h1[ch] = h1[ch] + 1
			end
		end

		h2 = Hash.new
		for i in 0..(word2.length - 1)
			ch = word2[i]
			if (h2[ch].nil?)
				h2[ch] = 1
			else
				h2[ch] = h1[ch] + 1
			end
		end

		h1.keys.each do |tmp|
			if (h2[tmp].nil? == true or h1[tmp].eql? h2[tmp] == false)
				return false
			end
		end		

		return true
	end
end

#is_anagrams?('cars', 'racs')
#is_anagrams?('cars', 'four')

def combine_anagrams(words)
	a = []
	words.each do |word|
		ck = false
		for i in 0..(a.length - 1)
			
			if (is_anagrams?(word, a[i][0]))
				a[i] = a[i] + [word]
				ck = true
				break					
			end
		end
		if (ck == false)
			a = a + [[word]]
		end				
	end
	return a
end

combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']

