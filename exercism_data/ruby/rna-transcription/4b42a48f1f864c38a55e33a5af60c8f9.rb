class Complement
	def self.of_dna( dna_strand )
		rna = ''
		dna_strand.each_char do |x|
			case x
			when 'G'
				x = 'C'
			when 'C'
				x = 'G'
			when 'T'
				x = 'A'
			when 'A'
				x= 'U'
			end
			rna << x
		end
		rna
	end
	def self.of_rna( rna_strand )
		dna = ''
		rna_strand.each_char do |x|
			case x
			when 'G'
				x = 'C'
			when 'C'
				x = 'G'
			when 'A'
				x = 'T'
			when 'U'
				x= 'A'
			end
			dna << x
		end
		dna
	end

end
