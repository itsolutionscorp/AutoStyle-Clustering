class Complement

	def self.of_dna(rna)
		# rna.gsub(/G/, 'C').gsub(/C/, 'G')
		letters = rna.split('').map do |letter|
			case letter
			when 'G'
				'C'
			when 'C'
				'G'
			when 'T'
				'A'
			when 'A'
				'U'
			end
		end
		letters.join
	end

	def self.of_rna(dna)
		# rna.gsub(/G/, 'C').gsub(/C/, 'G')
		letters = dna.split('').map do |letter|
			case letter
			when 'G'
				'C'
			when 'C'
				'G'
			when 'A'
				'T'
			when 'U'
				'A'
			end
		end
		letters.join
	end

end
