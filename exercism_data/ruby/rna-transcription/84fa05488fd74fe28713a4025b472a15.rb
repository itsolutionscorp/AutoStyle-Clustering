class Complement
	CONVERSION_LOOKUP =	{ "G" => "C",
												"C" => "G",
												"T" => "A",
												"A" => "U"}

	def self.of_dna(string)
		string.chars.map { |char| CONVERSION_LOOKUP[char] }.join
	end

	def self.of_rna(string)
		string.chars.map { |char| CONVERSION_LOOKUP.key(char)}.join
	end
end
