class Complement
	def self.of_dna(strand)
		sol = []
		rna = strand.split("")
		rna.map do |letter|
			case letter
			when "G" then sol << "C"
			when "C" then sol << "G"
			when "T" then sol << "A"
			when "A" then sol << "U"
			else sol << letter
			end
		end
		sol.join("") 
	end

	def self.of_rna(strand)
		sol = []
		rna = strand.split("")
		rna.map do |letter|
			case letter
			when "C" then sol << "G"
			when "G" then sol << "C"
			when "A" then sol << "T"
			when "U" then sol << "A"
			else sol << letter
			end
		end
		sol.join("") 
	end
end
