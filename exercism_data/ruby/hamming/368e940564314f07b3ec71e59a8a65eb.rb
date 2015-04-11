class Hamming

	def self.compute(strand1, strand2)
		hamming_distance = 0
		bases = self.shortest_strand(strand1, strand2)
		bases.each_index { |i| hamming_distance+= 1 if strand1[i] != strand2[i] }
		hamming_distance
	end

	private

	def self.shortest_strand(strand1, strand2)
		strand1.length <= strand2.length ? strand1.chars : strand2.chars
	end

end
