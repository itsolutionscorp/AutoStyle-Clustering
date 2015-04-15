class Complement
	def self.of_dna(dna)
		(dna.split(//).map do |c|
			case c
			when 'T'
				'A'
			when 'A'
				'U'
			when 'C'
				'G'
			when 'G'
				'C'
			end
		end).join('')
	end

	def self.of_rna(rna)
		(rna.split(//).map do |c|
			case c
			when 'A'
				'T'
			when 'U'
				'A'
			when 'C'
				'G'
			when 'G'
				'C'
			end
		end).join('')
	end
end
