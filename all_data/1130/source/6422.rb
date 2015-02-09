
def combine_anagrams(words)
	h = Hash.new
	words.each do |word|
		x = word.downcase.split(/\s*/).sort.to_s
		if(h.has_key?(x))
			h[x] = h[x] + [word]
		else
			h[x] = [word]
		end
	end
	l = []
	h.each_pair do |key,ana|
		l = l + [ana]
	end
	l
end

