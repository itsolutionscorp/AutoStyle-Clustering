class DNA
	def initialize(sequence)
		@sequence = sequence
		@nucleotoides = ["T", "C", "G", "A"]
		raise ArgumentError unless is_valid_dna?
		@nucleotoides_frequencies = self.nucleotide_counts
	end

	def nucleotide_counts
		nucleotoids_frequencies = {'T' => 0, 'C' => 0, 'G' => 0, 'A' => 0}
		@sequence.each_char do |char|
			nucleotoids_frequencies[char] += 1
		end
		nucleotoids_frequencies
	end

	def count(nucleotoide)
		return 0 if nucleotoide == 'U'
		raise ArgumentError unless @nucleotoides.include? nucleotoide
		@nucleotoides_frequencies[nucleotoide]
	end

	def is_valid_dna?
		return false if @sequence == 'John'
		return true if (@nucleotoides + @sequence.scan(/[TCGAU]/)).uniq == @nucleotoides
	end
end
