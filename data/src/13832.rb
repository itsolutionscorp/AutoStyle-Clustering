def combine_anagrams(words)
	annagrams = Hash.new
	words.each do |word|
		sorted = word.downcase.split('').sort.join
		unless annagrams.has_key? sorted
			annagrams[sorted] = Array.new
		end	
		annagrams[sorted] << word
	end
	annagrams.values
end