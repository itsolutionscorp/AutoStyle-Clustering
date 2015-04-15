class Complement
	DNA_RNA_MAP = {
		:DNA => {
			'C'=>'G',
			'G'=>'C',
			'T'=>'A',
			'A'=>'U'
		},
		:RNA => {
			'G'=>'C',
			'C'=>'G',
			'A'=>'T',
			'U'=>'A'
		}
	}

	def self.of_dna (dna_string)
		perform_compliment(dna_string, :DNA)
	end

	def self.of_rna (rna_string)
		perform_compliment(rna_string, :RNA)
	end

	private
	def self.perform_compliment (nucleotide_string, type)
		complement = ""
		nucleotide_string.each_char do |nucleotide|
			complement << DNA_RNA_MAP[type][nucleotide]
		end
		complement
	end
end
