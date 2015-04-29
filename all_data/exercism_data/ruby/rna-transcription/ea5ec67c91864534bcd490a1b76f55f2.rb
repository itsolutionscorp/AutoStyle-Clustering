class Complement
	def self.of_dna(dna)
		return dna.tr('G','X').tr('C','G').tr('A','U').tr('T','A').tr('X','C')
	end
	def self.of_rna(rna)
		return rna.tr('C','X').tr('G','C').tr('A','T').tr('U','A').tr('X','G')
	end
end
