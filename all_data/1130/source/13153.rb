# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
def combine_anagrams(words)
	groups = {}
	words.each do |w|
		w1 = w.downcase
		w1 = w1.chars.sort { |a, b| a.casecmp(b) } .join
		if (groups.has_key? w1)
			groups[w1] << w
		else
			groups[w1] = [w]
		end
	end
	groups.values
end