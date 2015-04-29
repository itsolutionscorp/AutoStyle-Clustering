class Complement
	def initialize
	end

	def self.of_dna(strand)
		result = []
	        dna_array = strand.split("")	
		dna_array.each do |d| 
			case d 
			when 'C'
				result << "G"
			when "G"
		       		result << "C"
			when "T"
	 			result << "A"
			when "A"
				result << "U"		
			end
		end
		result.join("")
	end

	def self.of_rna(strand)
		rna_array = strand.split("")
		result = [] 
		rna_array.each do |r| 
			case r
			when 'C'
				result << "G"
			when "G"
		       		result << "C"
			when "T"
	 			result << "A"
			when "A"
				result << "T"		
			when "U"
				result << "A"
			end
		end
		result.join("")		
	end
end
