class DNA
	DNA_NUCLEOTIDES = %w[A C G T]
	RNA_NUCLEOTIDES = %w[A C G U]

	def initialize(nucleotides)
		raise ArgumentError if (nucleotides.chars - DNA_NUCLEOTIDES).any?
		@nucleotides = nucleotides
	end

	def count(nucleotide)
		unless (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).include?(nucleotide)
			raise ArgumentError
		end
		@nucleotides.count(nucleotide)
	end

	def nucleotide_counts
		{
			'A' => count('A'),
			'T' => count('T'),
			'C' => count('C'),
			'G' => count('G')
		}
	end
end
