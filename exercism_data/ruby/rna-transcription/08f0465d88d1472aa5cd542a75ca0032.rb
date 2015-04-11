class Complement

	def self.of_dna(dna_string)
		rna_dna_convert("dna", dna_string)
	end
	
	def self.of_rna(rna_string)
		rna_dna_convert("rna", rna_string)
	end
	
	def self.rna_dna_convert(start_type, input_string)
		output_string = input_string.chars.map do |ch|
			case ch
			when "G"
				 "C"
			when "C"
				 "G"
			when "U"
				 "A"
			when "T"
				 "A"
			when "A"
				if start_type == 'rna'
				  "T"
				else
				  "U"
				end
			end
		end
		output_string.join
	end

end
