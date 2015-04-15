class Complement
	def self.of_dna(seq)
		complement = ''
		seq.split('').each do |base|
			case base
			when 'A'
				complement += 'U'
			when 'T'
				complement += 'A'
			when 'G'
				complement += 'C'
			when 'C'
				complement += 'G'
			end
		end
		complement
	end	

	def self.of_rna(seq)
		complement = ''
		seq.split('').each do |base|
			case base
			when 'A'
				complement += 'T'
			when 'U'
				complement += 'A'
			when 'G'
				complement += 'C'
			when 'C'
				complement += 'G'
			end
		end
		complement
	end	
end
