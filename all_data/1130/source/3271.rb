
def combine_anagrams(words)
	def ussj(word)
		return word.upcase().split("").sort().join("")
	end
	
	dic = words.reduce({}) {|acu, val| 
										if not acu.has_key?(ussj(val)) 
											acu[ussj(val)] = []
										end
										acu[ussj(val)].push(val); acu }
	return dic.values()
end
