=begin Pseudocode
	Think about using a switch statement to accomplish this problem

=end

class Complement

	def self.of_dna(strand)
		return transcribe_strand(strand, "DNA")
	end

	def self.of_rna(strand)
		return transcribe_strand(strand, "RNA")
	end

	def self.transcribe_strand(strand, strand_type)
		string_complement = ""
		strand.each_char do |char|
			string_complement += transcribe_char(char, strand_type)
		end
		return string_complement
	end

	def self.transcribe_char(char, strand_type)
		case 
		when "G" == char
			return "C"
		when "C" == char
			return "G"
		when "T" == char
			return "A"
		when "A" == char && strand_type == "DNA"
			return "U"
		when "A" == char && strand_type == "RNA"
			return "T"
		when 'U' == char
			return "A"
		end
	end

end
