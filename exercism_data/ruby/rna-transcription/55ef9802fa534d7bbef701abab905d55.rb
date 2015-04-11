class Complement
	def self.of_dna(dna)
		dnaRnaMap = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
		rna =''

		dna.each_char{|c| rna << dnaRnaMap[c] }

		rna
	end

	def self.of_rna(rna)
		rnaDnaMap = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
		dna =''

		rna.each_char{|c| dna << rnaDnaMap[c] }

		dna
	end

end
