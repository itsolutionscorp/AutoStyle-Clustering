class Complement

	@complements = {'G'=>'C','C'=>'G','T'=>'A','A'=>'U'}

	def self.of_dna(strand)
		strandArr = strand.split('')
		complementArr = strandArr.map do |nucleotide|
			nucleotide = @complements[nucleotide]
		end
		complement = complementArr.join('')
	end

	def self.of_rna(strand)
		strandArr = strand.split('')
		complementArr = strandArr.map do |nucleotide|
			nucleotide = @complements.key(nucleotide)
		end
		complement = complementArr.join('')
	end

end
