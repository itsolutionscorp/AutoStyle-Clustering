class Complement
	DNA = %w(G C T A)
	RNA = %w(C G A U)

	def self.of_dna nucleotide
		self.lookup('dna', nucleotide)
	end

	def self.of_rna nucleotide
		self.lookup('rna', nucleotide)
	end

	private
	def self.lookup(complement_of, sequence)
		complement_of = complement_of.downcase == 'dna' ? DNA : RNA
		antithesis = complement_of == DNA ? RNA : DNA
		output = []

		sequence.chars.each do |nucleotide|
			output << antithesis[complement_of.index(nucleotide)]
		end

		output.join
	end
end
