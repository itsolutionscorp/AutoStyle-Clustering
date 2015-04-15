class Hamming

	def self.compute(strand_one, strand_two)
		length = self.strand_length(strand_one, strand_two)
		base = strand_one.split("")
		comparison = strand_two.split("")
		count = 0
		
		(0...length).each do |x|
			count += 1 if base[x] != comparison[x]
		end
		count
	end

	private

	def self.strand_length(strand_one, strand_two)
		strand_two.length <= strand_one.length ? strand_two.length : strand_one.length
	end
end
