

class Complement
	def self.of_dna (strand)
		rna = ""
		for i in (0...strand.length)
			if strand[i] == 'C'
				rna << 'G'
			end
			if strand[i] == 'G'
				rna << 'C'
			end
			if strand[i] == 'T'
				rna << 'A'
			end
			if strand[i] == 'A'
				rna << 'U'
			end
		end
		#puts "#{rna}"
		rna
	end
	def self.of_rna (strand)
		dna = ""
		for i in (0...strand.length)
			case strand[i]
			when 'G'
				dna << 'C'
			when 'C'
				dna << 'G'
			when 'A'
				dna << 'T'
			when 'U'
				dna << 'A'
			end
		end
		#puts "#{dna}"
		dna
	end
end

#c=Complement.new();
#c.of_dna('GCTA')
#c.of_rna('CGAU')
