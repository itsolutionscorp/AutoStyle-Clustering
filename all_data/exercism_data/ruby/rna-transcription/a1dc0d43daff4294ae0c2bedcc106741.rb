module Complement

	COMPLEMENT = {
		"G" => "C",
		"C" => "G",
		"T" => "A",
		"A" => "U"
	}

	def self.of_dna sequence
		rna_to_dna(sequence)
	end

	def self.of_rna sequence
		dna_to_rna(sequence)
	end

private

	def self.dna_to_rna(sequence)
		swap_char_using_dict(sequence, COMPLEMENT.invert)
	end

	def self.rna_to_dna(sequence)
		swap_char_using_dict(sequence, COMPLEMENT)
	end

	def self.swap_char_using_dict(string, dict)
		complement_sequence = ""
		string.each_char do |char|
			complement_sequence << dict[char]
		end
		complement_sequence
	end

end
