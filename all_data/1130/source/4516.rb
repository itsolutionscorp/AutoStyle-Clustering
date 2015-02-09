def combine_anagrams(words)
h = Hash.new
a = Array.new
words.each do |word|
	w = word.chars.sort_by(&:downcase).join
	if h.member?(w.downcase)
		a[h[w.downcase]].push(word)
	else
		b = Array[word]
		a.push(b)
		h[w.downcase] = a.index(b)
	end
end
a
end