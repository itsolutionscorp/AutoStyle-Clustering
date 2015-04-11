class Complement
	def Complement.of_dna(strand)
		length = strand.length
		complement = "X"*length
		for i in 0...length
			case strand[i]
			when 'G'
				complement[i] = 'C'
			when 'C'
				complement[i] = 'G'
			when 'T'
				complement[i] = 'A'
			when 'A'
				complement[i] = 'U'
			end
		end
		return complement
	end
	def Complement.of_rna(strand)
		length = strand.length
		complement = "X"*length
		for i in 0...length
			case strand[i]
			when 'C'
				complement[i] = 'G'
			when 'G'
				complement[i] = 'C'
			when 'A'
				complement[i] = 'T'
			when 'U'
				complement[i] = 'A'
			end
		end
		return complement
	end
end
