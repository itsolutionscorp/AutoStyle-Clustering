# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`

class Complement
	
	def self.of_dna(dna)
		rna = ""
		dna.length.times do |i|
			case dna[i]
			when "G"
				rna[i] = "C"
			when "C"
				rna[i] = "G"
			when "T"
				rna[i] = "A"
			when "A"
				rna[i] = "U"
			end
		end
		rna
	end

	def self.of_rna(rna)
		dna = ""
		rna.length.times do |i|
			case rna[i]
			when "C"
				dna[i] = "G"
			when "G"
				dna[i] = "C"
			when "A"
				dna[i] = "T"
			when "U"
				dna[i] = "A"
			end
		end
		dna
	end
end
