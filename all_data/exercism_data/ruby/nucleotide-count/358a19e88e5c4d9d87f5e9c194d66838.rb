class DNA
	def initialize(dna)
		@dna = (!dna.match(/[^ACTG]/) || dna.empty?) ? dna : (raise ArgumentError)
	end

	def count(letter)
		letter.match(/[ACGTU]/) ? @dna.chars.count(letter) : (raise ArgumentError)
	end

	def nucleotide_counts
		counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
		@dna.chars.each_with_object(counts) { |char, counts| counts[char] += 1 }
	end
end
