class Hamming
	def self.compute(stranda, strandb)
		diff = 0
		0.upto(stranda.length-1) { |i|
			diff += stranda[i] == strandb[i] ? 0 : 1
		}
		return diff
	end
end
