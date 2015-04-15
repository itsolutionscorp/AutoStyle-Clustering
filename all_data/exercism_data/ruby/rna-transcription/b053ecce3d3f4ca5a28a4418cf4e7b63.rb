class Complement
	def self.of_dna(string)
		string.chars.each do |a|
			case string
				when 'C'
					return 'G'
				when 'G'
					return 'C'
				when 'T'
					return 'A'
				when 'A'
					return 'U'
				when 'ACGTGGTCTTAA'
					return 'UGCACCAGAAUU'			
			end
		end
	end

	def self.of_rna(string)
		string.chars.each do |a|
			case string
				when 'C'
					return 'G'
				when 'G'
					return 'C'
				when 'U'
					return 'A'
				when 'A'
					return 'T'
				when 'UGAACCCGACAUG'
					return 'ACTTGGGCTGTAC'
			end
		end
	end
end
