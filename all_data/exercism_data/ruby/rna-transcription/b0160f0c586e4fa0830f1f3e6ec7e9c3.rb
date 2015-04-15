class Complement
	def self.conversion_lookup
		{ "G" => "C",
			"C" => "G",
			"T" => "A",
			"A" => "U"
		}
	end

	def self.of_dna(string)
		string.chars.map { |char| conversion_lookup[char] }.join
	end

	def self.of_rna(string)
		string.chars.map { |char| conversion_lookup.key(char)}.join
	end
end
