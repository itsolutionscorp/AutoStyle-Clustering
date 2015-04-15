def combine_anagrams(words)
	anagrams = []
	hash = Hash.new{ |h,k| h[k] =[] }
	words.each do |key|
		hash[key.downcase.split(//).sort.join] << key
	end
	hash.each_pair do |h,k|
		anagrams << k
	end
	p anagrams
	return anagrams
end
