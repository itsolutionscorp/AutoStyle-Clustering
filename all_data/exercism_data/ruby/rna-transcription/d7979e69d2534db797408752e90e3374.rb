class Complement
	def self.of_dna(strand)
		strand.chars.map do |n|
			case n
			when 'G'
				'C'
			when 'C'
				'G'
			when 'T'
				'A'
			when 'A'
				'U'
			end
		end.join('')
	end

	def self.of_rna(strand)
		strand.chars.map do |n|
			case n
			when 'G'
				'C'
			when 'C'
				'G'
			when 'A'
				'T'
			when 'U'
				'A'
			end
		end.join('')
	end


end
