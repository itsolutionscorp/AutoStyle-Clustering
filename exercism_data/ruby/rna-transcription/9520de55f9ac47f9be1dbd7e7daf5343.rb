class Complement
	@dnaRnaMap = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	def self.of_dna(dna)		
		dna.split('').map{|c| @dnaRnaMap[c]}.join()
	end

	def self.of_rna(rna)
		rnaDnaMap = @dnaRnaMap.invert
		rna.split('').map{|c| rnaDnaMap[c] }.join()
	end

end
