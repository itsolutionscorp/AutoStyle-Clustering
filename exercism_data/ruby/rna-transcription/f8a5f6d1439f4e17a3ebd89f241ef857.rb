class Complement
	def self.of_dna(dnaSequence)
		result = ''
		(0..dnaSequence.length-1).each do |i|
			case dnaSequence[i]
			when 'G'
				result << 'C'
			when 'C'
				result << 'G'
			when 'T' 
				result << 'A'
			when 'A'
				result << 'U'
			end
		end
		return result
	end

	def self.of_rna(rnaSequence)
		result = ''
		(0..rnaSequence.length-1).each do |i|
			case rnaSequence[i]
			when 'C'
				result << 'G'
			when 'G'
				result << 'C'
			when 'A' 
				result << 'T'
			when 'U'
				result << 'A'
			end
		end
		return result
	end
end
				
