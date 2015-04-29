class Hamming
	def self.compute x, y
		return 0 if x == y

		s1 = x.split("")
		s2 = y.split("")
		hammingDistance = 0

		s1.each_with_index { | val, index |
			break if index >= s2.length
			hammingDistance += 1 if val != s2[index]
		}
		return hammingDistance
	end
end
