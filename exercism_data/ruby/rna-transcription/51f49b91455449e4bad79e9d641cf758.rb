DNA_RNA_STRAND = {
	'G' => 'C',
	'C' => 'G', 
	'T' => 'A', 
	'A' => 'U'
}

class Complement 
	def self.of_dna(strand)
		form (strand) { |i| DNA_RNA_STRAND.fetch(i) }
	end
	
	def self.of_rna(strand) 
		form (strand) { |i|	DNA_RNA_STRAND.invert.fetch(i)}
	end
	
	private
	def self.form(strand)
		strand.chars.map {|i| yield i }.join
	end
end
