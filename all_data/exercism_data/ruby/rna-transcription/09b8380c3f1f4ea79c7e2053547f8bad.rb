class Complement

	def self.of_dna(sequence)

		if sequence.include?('U')
			raise ArgumentError
		end

		sequence.upcase

		sequence.gsub!(/[ATGC]/, 'A' => 'U', 
								 'T' => 'A', 
								 'G' => 'C', 
								 'C' => 'G'
							 )

		return sequence 

	end



	def self.of_rna(sequence)

		case sequence
		when /T/
			raise ArgumentError
		end

		sequence.upcase

		sequence.gsub!(/[UACG]/, 'U' => 'A', 
								 'A' => 'T', 
								 'C' => 'G', 
								 'G' => 'C'
							 )

		return sequence 
	

	end



end
