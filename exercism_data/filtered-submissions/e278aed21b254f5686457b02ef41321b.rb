class Hamming
	def compute(strand1, strand2)
		(strand1.split("") - strand2.split("")).count
	end
end
