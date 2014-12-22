def combine_anagrams(words)
	split = []
	words.each do |word|
		processed = word.downcase.split(//).sort
		split << processed unless split.include?(processed)
	end

	out = []

	split.each do |lets|
		temp = words.select { |x| x.downcase.split(//).sort == lets }
		out << temp
	end

	return out
end