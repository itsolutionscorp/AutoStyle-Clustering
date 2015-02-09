def combine_anagrams(words)
	aux = Array.new
	res = Array.new
	pru = Array.new
	aux2 =Array.new
	words.each do |elem|
		pru << elem.split(/w*/).sort*""
	end

	hash = Hash.new { |h,k| h[k] = [] }

	pru.each_with_index do |val, idx|
	  hash[val] << words[idx]
	end
	
	hash.each_key do |key|
		res << hash[key]
	end
	
	return res
end


