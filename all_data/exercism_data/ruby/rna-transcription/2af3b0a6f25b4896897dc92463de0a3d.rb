# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`

class Complement
	def self.of_dna (nucleotide)
		return nucleotide.tr('GCTA', 'CGAU')
	end
	def self.of_rna (nucleotide)
		return nucleotide.tr('CGAU', 'GCTA')
	end
end
