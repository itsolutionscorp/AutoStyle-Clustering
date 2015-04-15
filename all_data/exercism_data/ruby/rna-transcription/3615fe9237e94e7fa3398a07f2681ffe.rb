class Complement
	DNAMAP = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'
	}
	RNAMAP = DNAMAP.invert

	def self.of_dna(d)
		d.split('').map{|i| DNAMAP[i]}.join
	end

	def self.of_rna(r)
		r.split('').map{|i| RNAMAP[i]}.join
	end
end
