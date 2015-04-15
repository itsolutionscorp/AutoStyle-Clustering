class Complement
	def self.of_dna(strand)
		strand.gsub /./, DNA_RNA_COMPLEMENTARY_NUCLEOTIDES
	end

	def self.of_rna(strand)
		strand.gsub /./, RNA_DNA_COMPLEMENTARY_NUCLEOTIDES
	end

	private
	DNA_RNA_COMPLEMENTARY_NUCLEOTIDES = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'
	}
	RNA_DNA_COMPLEMENTARY_NUCLEOTIDES = 
		DNA_RNA_COMPLEMENTARY_NUCLEOTIDES.invert
end
