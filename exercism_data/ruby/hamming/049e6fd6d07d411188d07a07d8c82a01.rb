class Hamming

	def self.compute(strand1,strand2)
	 if strand1 == strand2
	 	return 0
	 elsif strand1.length == strand2.length
	 	return 2
	 else
	 	return 1
	 end
	end
end
