class Complement
  @@dna_map = ['GCAT', 'CGUA']
  @@rna_map = @@dna_map.reverse
  
	def self.of_dna(strands)
		strands.tr(*@@dna_map)
	end

	def self.of_rna(strands)
		strands.tr(*@@rna_map)
	end
end
