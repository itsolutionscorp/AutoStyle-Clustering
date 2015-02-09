def combine_anagrams(words)
	hash = Hash.new
	words.each do |word|
		key = word.downcase.chars.sort.join("")
		if hash.key?(key) == false
      hash[key] = [word]
		else
      hash[key] << word
		end
	end

  anagrams = Array.new
	hash.each do |key, value|
    anagrams << value
	end

	return anagrams
end