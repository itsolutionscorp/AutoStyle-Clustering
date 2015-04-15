RNA_DNA_STRAND = {:c => 'G', :g => 'C', :a => 'T', :u => 'A'}
DNA_RNA_STRAND = {:g => 'C', :c => 'G', :t => 'A', :a => 'U'}

class Complement 
	def self.of_dna(dna_strand)
		rna_strand = []
		dna_strand.split('').each { |i|	rna_strand.push(DNA_RNA_STRAND[i.downcase.to_sym])}
		rna_strand.join
	end
	
	def self.of_rna(rna_strand) 
		dna_strand = []
		rna_strand.split('').each { |i|	dna_strand.push(RNA_DNA_STRAND[i.downcase.to_sym])}
		dna_strand.join
	end
end
