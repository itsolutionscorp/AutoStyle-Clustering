class Complement
	@@DNA_TO_RNA = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
	@@RNA_TO_DNA = {'G'=>'C', 'C'=>'G', 'A'=>'T', 'U'=>'A'}

	def self.of_dna(dna)
		dna.gsub(/./, @@DNA_TO_RNA)
	end

	def self.of_rna(rna)
		rna.gsub(/./, @@RNA_TO_DNA)
	end
end
