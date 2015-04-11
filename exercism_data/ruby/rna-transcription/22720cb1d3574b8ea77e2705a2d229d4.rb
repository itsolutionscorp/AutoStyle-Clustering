class Complement

	@complements = {'G'=>'C','C'=>'G','T'=>'A','A'=>'U'}

	def self.of_dna(strand)
		old_strand = strand.split('')
		new_strand = old_strand.map do |nucleotide|
			nucleotide = @complements[nucleotide]
		end
		new_strand.join('')
	end

	def self.of_rna(strand)
		old_strand = strand.split('')
		new_strand = old_strand.map do |nucleotide|
			nucleotide = @complements.key(nucleotide)
		end
		new_strand.join('')
	end

end
