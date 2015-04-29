class Hamming

	def self.compute(strand_one, strand_two)
		length = self.strand_length(strand_one, strand_two)
		count = 0
		length.times do |x|
			count += 1 if strand_one[x] != strand_two[x]
		end
		count
	end

	private

	def self.strand_length(strand_one, strand_two)
		strand_two.length <= strand_one.length ? strand_two.length : strand_one.length
	end
end
