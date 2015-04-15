class Complement
  DNA = 'GCTA'
  RNA = 'CGAU'

	def self.of_dna(strand)
		strand.tr(DNA,RNA)
	end

	def self.of_rna(strand)
		strand.tr(RNA, DNA)
	end
end
