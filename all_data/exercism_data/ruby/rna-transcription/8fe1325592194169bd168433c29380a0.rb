class Complement
	DNA_RNA_MAPS = ['GCTA', 'CGAU']

	def self.of_dna(seq)
		seq.tr(*DNA_RNA_MAPS)
	end

	def self.of_rna(seq)
		seq.tr(*DNA_RNA_MAPS.reverse)
	end
end
