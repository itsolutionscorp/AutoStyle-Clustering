class DNA

	DNA_NUCLEOTIDES = %w{A C G T}
  RNA_NUCLEOTIDES = %w{A C G U}
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES | RNA_NUCLEOTIDES

	def initialize(dna_string)
		@dna_string = dna_string
		unless @dna_string.each_char.all? { |nucleotide| DNA_NUCLEOTIDES.include?(nucleotide) }
			raise ArgumentError
		end
	end

	def count(nucleotide)
		unless ALL_NUCLEOTIDES.include?(nucleotide)
			raise ArgumentError
		end

		@dna_string.count(nucleotide)
	end

	def nucleotide_counts
		DNA_NUCLEOTIDES.each_with_object({}) { |nucleotide, hash| hash[nucleotide] = count(nucleotide)}
	end


end
