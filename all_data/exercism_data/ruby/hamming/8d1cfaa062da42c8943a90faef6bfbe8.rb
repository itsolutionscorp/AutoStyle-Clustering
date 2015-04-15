class Hamming
	def self.compute (dna1, dna2)
		nucleobases1 = dna1.split("")
		nucleobases2 = dna2.split("")
		result = 0

		(0..dna1.length).each do |i|
			if nucleobases1[i] != nucleobases2[i]
			result += 1
			end
		end

		result
	end
end
