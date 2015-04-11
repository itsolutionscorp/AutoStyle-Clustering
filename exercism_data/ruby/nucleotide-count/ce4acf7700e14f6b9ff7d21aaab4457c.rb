class DNA

	def initialize(string)
		@dna = string
		validate(@dna)
		@dna_counts = dna_counts
	end

	def count(nucleotide)
		validate(nucleotide)
		@dna_counts[nucleotide]
	end

	def nucleotide_counts
		@dna_counts
	end

	private

	def validate(string)
		raise ArgumentError unless string.match(/^[ACTG]*$/)
	end

	def dna_counts
		counts_hash = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
		@dna.split(//).each { |x| counts_hash[x] += 1 }
		counts_hash
	end

end
