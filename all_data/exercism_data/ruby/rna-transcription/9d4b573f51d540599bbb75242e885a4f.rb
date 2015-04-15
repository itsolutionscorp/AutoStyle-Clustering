class Complement
	DNAMAP = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'
	}
	RNAMAP = DNAMAP.invert

	def self.of_dna(d)
		r = ''
		d.split('').each{|i| r += DNAMAP[i]}
		r
	end

	def self.of_rna(r)
		d = ''
		r.split('').each{|i| d += RNAMAP[i]}
		d
	end
end
