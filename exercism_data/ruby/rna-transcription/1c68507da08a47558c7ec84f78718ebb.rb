class Complement
	def self.match_rna(nucleo)
		if nucleo == 'A'
			'T'
		elsif nucleo == 'U'
			'A'
		elsif nucleo == 'C'
			'G'
		elsif nucleo == 'G'
			'C'
		end
	end

	def self.of_rna(sequence)
		arr = sequence.split('')
		complement = ''
		arr.each do |nucleo|
			complement = complement + self.match_rna(nucleo)
		end
		return complement
	end

	def self.match_dna(nucleo)
		if nucleo == 'A'
			'U'
		elsif nucleo == 'T'
			'A'
		elsif nucleo == 'C'
			'G'
		elsif nucleo == 'G'
			'C'
		end
	end

	def self.of_dna(sequence)
		arr = sequence.split('')
		complement = ''
		arr.each do |nucleo|
			complement = complement + self.match_dna(nucleo)
		end
		return complement
	end
end
