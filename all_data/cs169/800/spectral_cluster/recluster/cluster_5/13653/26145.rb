def combine_anagrams(words)
	sorted =[]
	antagrams = Array.new
	words.each do |word|
		sorted.push word.downcase.chars.sort.join
	end
	sorted = sorted.uniq
 	puts sorted
	puts "____"
	 sorted.each do |sword|
	 	temp =[]
		words.each do |word|
			if (word.downcase.chars.sort.join==sword)
				temp.push word
			end
		end
		puts temp
		antagrams.push temp
	 end
	antagrams
end
