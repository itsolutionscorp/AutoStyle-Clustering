def combine_anagrams(words)
	hash = Hash.new
	words.each do |str|
		sort = str.chars.sort.join
		if (hash[sort]== nil)
			hash[sort] = Array.new
		end
		ar = hash[sort]
		ar[ar.length] = str
		hash[sort] = ar
		ar = nil
	end
	hash.values
end


