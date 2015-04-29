class DNA
	attr_reader :dna
	
	def initialize(dna)
		@dna = dna
	end

	def hamming_distance(strand)
		min_length = [@dna.length, strand.length].min
		(0...min_length).each_with_object([]) { |i, indexes| indexes << i if mutation?(@dna[i], strand[i]) }.count
	end

	private
		def mutation?(a, b)
			a != b
		end
end
