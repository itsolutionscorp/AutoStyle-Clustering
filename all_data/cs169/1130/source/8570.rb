class Array
	def combine_anagrams
		dict = Hash.new
		self.each { |k|
			key = k.downcase.split("").sort.join
			if dict.has_key?(key) == false
				dict[key] = []			
			end
			dict[key].push(k)
		}
		res = []
		dict.each { |d, (k, v)|
			res.push(dict[d])
		}
		return res.reverse
	end
end

def combine_anagrams(words)
	return words.combine_anagrams
end

puts ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'].combine_anagrams.inspect
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
puts ['HeLLo', 'hello'].combine_anagrams.inspect
puts ['A', 'a'].combine_anagrams.inspect

