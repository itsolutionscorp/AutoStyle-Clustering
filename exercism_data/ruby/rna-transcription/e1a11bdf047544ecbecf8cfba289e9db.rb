class Complement
  DNA = 'GCTA'
  RNA = 'CGAU'

	class << self
		def of_dna(dna)
			dna.tr(DNA, RNA)
		end

		def of_rna(rna)
			rna.tr(RNA, DNA)
		end
	end
end
