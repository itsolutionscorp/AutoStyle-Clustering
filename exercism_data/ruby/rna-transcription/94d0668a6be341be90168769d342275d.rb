class Complement

	def self.of_dna(dna)
		if dna.length == 0
			puts "Not a valid RNA string\n"
		end
		s = ""
		dna.each_char do |c|
			c.upcase
			case c
			when 'A'
				s << 'U'
			when 'T'
				s << 'A'
			when 'C'
				s << 'G'
			when 'G'
				s << 'C'
			end
		end
		s
	end

	def self.of_rna(rna)
		if rna.length == 0
			puts "Not a valid RNA string\n"
		end
		s = ""
		rna.each_char do |c|
			c.upcase
			case c
			when 'A'
				s << 'T'
			when 'U'
				s << 'A'
			when 'C'
				s << 'G'
			when 'G'
				s <<'C'
			end
		end
		s
	end

end
