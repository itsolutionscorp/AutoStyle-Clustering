class Complement
	def self.of_dna(dna_strand)
		dna_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
		dna_strand_array = dna_strand.split(//)

		converted_rna = do_conversion(dna_rna, dna_strand_array)

		return converted_rna.join('')
	end

	def self.of_rna(rna_strand)
		rna_dna = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
		rna_strand_array = rna_strand.split(//)

		converted_dna = do_conversion(rna_dna, rna_strand_array)

		return converted_dna.join('')
	end

	private
		def self.do_conversion(conversion_hash, strand_array)
			converted_strand = Array.new
			for nucleotide in strand_array
				converted_strand.push(conversion_hash[nucleotide])
			end

			return converted_strand
		end
end
