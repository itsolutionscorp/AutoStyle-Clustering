DNA_RNA_STRAND = {
	'G' => 'C',
	'C' => 'G', 
	'T' => 'A', 
	'A' => 'U'
}

class Complement 
	def self.of_dna(dna_strand)
		rna_strand = []
		dna_strand.chars.map { |i|	rna_strand.push(DNA_RNA_STRAND.fetch(i))}
		rna_strand.join
	end
	
	def self.of_rna(rna_strand) 
		dna_strand = []
		rna_strand.chars.map { |i|	dna_strand.push(DNA_RNA_STRAND.invert.fetch(i))}
		dna_strand.join
	end
end
