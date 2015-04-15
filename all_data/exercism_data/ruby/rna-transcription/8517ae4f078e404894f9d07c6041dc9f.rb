class Complement
	def self.of_dna(strand)
		rna = ""
		for i in 0..strand.length-1 do
			case strand[i]
			when "G"
				rna += "C"
			when "C"
				rna += "G"
			when "T"
				rna += "A"
			when "A"
				rna += "U"
			else
				rna += strand[i]
			end
		end
		rna
	end

	def self.of_rna(strand)
		dna = ""
		for i in 0..strand.length-1 do
			case strand[i]
			when "C"
				dna += "G"
			when "G"
				dna += "C"
			when "A"
				dna += "T"
			when "U"
				dna += "A"
			else
				dna += strand[i]
			end
		end
		dna
	end
end
