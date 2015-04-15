class Complement
	def self.of_dna dna
		result = ""
		dna.split("").each do |i|
			case i
			when 'G' then result += 'C'
			when 'C' then result += 'G'
			when 'T' then result += 'A'
			when 'A' then result += 'U'
			end
		end
		result
	end

	def self.of_rna dna
		result = ""
		dna.split("").each do |i|
			case i
			when 'C' then result += 'G'
			when 'G' then result += 'C'
			when 'A' then result += 'T'
			when 'U' then result += 'A'
			end
		end
		result
	end
end
