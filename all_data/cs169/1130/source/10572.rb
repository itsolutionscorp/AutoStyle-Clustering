class String
	def chars
		self.split("")
	end
end

def count_words(string)
	h = Hash.new()
	h.default = 0
	string.downcase.scan(/\w+/).each {
		|word| h[word] = h[word] + 1
	}
	return h
end

def combine_anagrams(words)
	#newlist = words.map { 
	#		|w| w.chars.sort{ |a, b| a.casecmp(b) }.join 
	#	  }.sort{ |a, b| a.casecmp(b) }.join(" ")
	newlist = words.map {
			|w| w.downcase.chars.sort.join
		  }.sort.join(" ")
	
	hash = count_words(newlist)
	hash.each {|k, v| hash[k] = Array.[]}
	
	words.each do |w|
		k = w.downcase.chars.sort{|a, b| a.casecmp(b)}.join
		#puts k, hash[k]
		hash[k] << w
	end
	return hash.values
end
