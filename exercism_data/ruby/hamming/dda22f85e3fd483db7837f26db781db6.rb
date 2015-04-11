class Hamming
	
	def self.compute(strand1, strand2)
		strand_lengths = [strand1.length, strand2.length]
		strand_differences = []
		0.upto(strand_lengths.min - 1) do |i|
			strand_differences.push(strand1[i] == strand2[i])
		end

	strand_differences.count(false)
	end
end
