class Complement
	DNAMAP = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'
	}
	RNAMAP = DNAMAP.invert

	def self.of_dna(d)
		complement(d, DNAMAP)
	end

	def self.of_rna(r)
		complement(r, RNAMAP)
	end

	def self.complement(strand, map)
		strand.split('').map{|i| map[i]}.join
	end
end
