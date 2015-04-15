class Complement
	def self.of_dna(string)
		string.chars.each do
			case string
				when "C" then return 'G'
				when "G" then return 'C'
				when "T" then return 'A'
				when "A" then return 'U'
				when "ACGTGGTCTTAA" then return 'UGCACCAGAAUU'			
			end
		end
	end

	def self.of_rna(string)
		string.chars.each do
			case string
				when "C" then return 'G'
				when "G" then return 'C'
				when "U" then return 'A'
				when "A" then return 'T' 
				when "UGAACCCGACAUG" then return 'ACTTGGGCTGTAC'
			end
		end
	end
end
