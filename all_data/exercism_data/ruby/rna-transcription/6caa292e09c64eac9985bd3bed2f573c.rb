class Complement
	def self.of_dna(strand)
		length = strand.length - 1
		rna = ""
		(0..length).each do |x|
			case strand[x]
			when "G"
				rna << "C"
			when "C"
				rna << "G"
			when "T"
				rna << "A"
			when "A"
				rna << "U"
			else
				rna << strand[x]
			end
		end
		rna
	end
	def self.of_rna(strand)
		length = strand.length - 1
		dna = ""
		(0..length).each do |x|
			case strand[x]
			when "C"
				dna << "G"
			when "G"
				dna << "C"
			when "A"
				dna << "T"
			when "U"
				dna << "A"
			else
				dna << strand[x]
			end
		end
		dna
	end
end
