class Complement
	@@dna_nucleotides = ['G', 'C', 'T', 'A']
	@@rna_nucleotides = ['C', 'G', 'A', 'U']

	def self.of_dna(dna)
		self.convert(dna, @@dna_nucleotides, @@rna_nucleotides)
	end

	def self.of_rna(rna)
		self.convert(rna, @@rna_nucleotides, @@dna_nucleotides)
	end

	private

		def self.convert(strand, from, to)
			strand.chars.map do |nucleotide|
				to[from.find_index nucleotide]
			end.join('')
		end
end
