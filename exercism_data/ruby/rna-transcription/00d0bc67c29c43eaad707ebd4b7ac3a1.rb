class Complement
	@complements = {'G' => 'C',
			'C' => 'G',
			'T' => 'A',
			'A' => 'U'}

        def self.of_dna(dna)
		dna.chars.map{ |n| @complements[n] }.join('')
        end

	def self.of_rna(rna)
		rna = rna.chars.map{ |n| @complements.invert[n] }.join('')
	end
end # end class Complement
