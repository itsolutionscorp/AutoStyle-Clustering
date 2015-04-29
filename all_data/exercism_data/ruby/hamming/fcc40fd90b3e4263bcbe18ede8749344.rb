module Hamming
	def self.compute(a,b)
		hamming = 0
		for i in 0..([a.length,b.length].min-1)
			unless a[i] == b[i]
				hamming = hamming + 1
			end
		end
		return hamming
	end
end
