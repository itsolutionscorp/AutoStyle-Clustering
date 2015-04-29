class Complement

	DNA_RNA = {:C => "G", :G => "C", :T => "A", :A => "U"}
	RNA_DNA = {:C => "G", :G => "C", :U => "A", :A => "T"}

	def self.of_dna(strand)
		strand.chars.map { |e| DNA_RNA[e.to_sym] }.join
	end

	def self.of_rna(strand)
		strand.chars.map { |e| RNA_DNA[e.to_sym] }.join
	end
	
end
