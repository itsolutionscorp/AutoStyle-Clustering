class Complement
	
	@@dna_to_rna = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

	def self.of_dna of_dna
		of_dna.each_char.collect { |x| @@dna_to_rna[x] }.join
	end

	def self.of_rna of_rna
		of_rna.each_char.collect { |x| @@dna_to_rna.key(x) }.join
	end
end
