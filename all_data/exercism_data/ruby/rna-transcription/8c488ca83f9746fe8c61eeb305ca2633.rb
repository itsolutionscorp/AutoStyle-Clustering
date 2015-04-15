class Complement
	def self.get_dna_complements
		{'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	end
	def self.get_rna_complements
		get_dna_complements.each_with_object({}) do |(key, value), acc|
			acc[value] ||= []
			acc[value] << key
		end
	end
	def self.of_dna(code)
		transcribe_code(code, get_dna_complements)
	end
	def self.of_rna(code)
		transcribe_code(code, get_rna_complements)
	end
	def self.transcribe_code(code, complements)
		code.chars.map do |value|
			value = complements[value]
		end.join
	end
end
