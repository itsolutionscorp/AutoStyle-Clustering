def combine_anagrams(words)
	hash = Hash.new{|h, k| h[k] = []}
	words.each{|w| hash[w.downcase.chars.sort.join] << w}
	a = []
	hash.keys.each{|k| a << hash[k]}
	return a
end

w = ['cArs', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream']

p combine_anagrams(w)