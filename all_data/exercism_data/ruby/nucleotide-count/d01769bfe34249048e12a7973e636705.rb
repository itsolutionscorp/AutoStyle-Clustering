class RNA
	NUCLEOTIDES = %w[A C G U]
end

class DNA
	NUCLEOTIDES = %w[A C G T]

	def initialize(nucleotides)
		raise ArgumentError if (nucleotides.chars - NUCLEOTIDES).any?
		@nucleotides = nucleotides
	end

	def count(nucleotide)
		unless (NUCLEOTIDES + RNA::NUCLEOTIDES).include?(nucleotide)
			raise ArgumentError
		end
		@nucleotides.count(nucleotide)
	end

	def nucleotide_counts
		NUCLEOTIDES.each_with_object({}) { |str, hsh| hsh[str] = count(str) }
	end
end
