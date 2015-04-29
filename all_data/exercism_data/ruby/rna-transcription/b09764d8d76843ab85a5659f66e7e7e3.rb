class Complement
	CONVERSION_LOOKUP =	{ "G" => "C",
												"C" => "G",
												"T" => "A",
												"A" => "U"}

	def self.of_dna(string)
		lookup(string, CONVERSION_LOOKUP)
	end

	def self.of_rna(string)
		lookup(string, CONVERSION_LOOKUP.invert)
	end

	private

	def self.lookup(string, lookup_hash)
		string.chars.map{|char| lookup_hash[char]}.join
	end
end
