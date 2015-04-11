class Complement
	@@h = {'G'=>'C','C'=>'G','T'=>'A','A'=>'U'}
	
	def self.of_dna(x)
		rna = ''
		x.each_char { |c| rna += @@h[c] }
		rna
	end
	def self.of_rna(x)
		dna = ''
		hh = @@h.invert
		x.each_char { |c| dna += hh[c] }
		dna
	end
end
