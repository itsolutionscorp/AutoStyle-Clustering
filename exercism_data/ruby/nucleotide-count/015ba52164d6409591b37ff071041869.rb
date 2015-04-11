class DNA

	def initialize(string)
		@dna = string
		validate
		@dna_counts = dna_counts
	end

	def count(nucleotide)
		raise ArgumentError unless nucleotide.match(/[ATCG]/)
		@dna_counts[nucleotide]
	end

	def nucleotide_counts
		@dna_counts
	end

	private

	def validate
		raise ArgumentError unless @dna.match(/^[ACTG]*$/)
	end

	def dna_counts
		counts_hash = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
		@dna.split(//).each { |x| counts_hash[x] += 1 }
		counts_hash
	end

end
