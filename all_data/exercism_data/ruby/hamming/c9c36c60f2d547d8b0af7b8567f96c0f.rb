class Hamming
	def self.compute(strand_1, strand_2)
		length = [strand_1.size, strand_2.size].min
		(0...length).inject(0) do |sum, index|
			strand_1[index] == strand_2[index] ? sum : sum += 1
		end
	end
end
