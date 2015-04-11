class Complement
	DNA = 'GCTA'
	RNA = 'CGAU'

	def self.of_dna nucleotide
		nucleotide.tr(DNA, RNA)
	end

	def self.of_rna nucleotide
		nucleotide.tr(RNA, DNA)
	end
end
