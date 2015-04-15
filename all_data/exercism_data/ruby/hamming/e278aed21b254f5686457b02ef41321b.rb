class Hamming
	def self.compute(strand1, strand2)
		(strand1.split("") - strand2.split("")).count
	end
end
