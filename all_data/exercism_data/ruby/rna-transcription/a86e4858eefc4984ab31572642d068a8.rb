class Complement
	
	DNA_TO_RNA = {
		"G" => "C",
		"C" => "G",
		"T" => "A",
		"A"	=> "U"
	}
	RNA_TO_DNA = DNA_TO_RNA.invert

	def self.of_dna strand
		strand.chars.map { |acid| DNA_TO_RNA[acid] }.join
	end

	def self.of_rna strand
		strand.chars.map { |acid| RNA_TO_DNA[acid] }.join
	end
end
