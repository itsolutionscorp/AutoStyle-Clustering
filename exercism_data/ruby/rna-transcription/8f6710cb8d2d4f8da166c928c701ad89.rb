class Complement
	Rna_to_dna = {'G' => "C", "C" => "G", "T" =>"A", "A" => "U"}
	Dna_to_rna = {'C' => "G", "G" => "C", "A" =>"T", "U" => "A"}

	def self.of_dna(rna_strand)
		self.convert_strand rna_strand, Rna_to_dna
	end
	def self.of_rna(dna_strand)
		self.convert_strand dna_strand, Dna_to_rna
	end
	def self.convert_strand(source_strand, pattern)
		result_strand = []
		source_strand.split('').each_with_index { |nucleotide, index|
			result_strand[index]=pattern[nucleotide]
		}
		result_strand.join('')
	end
end
