class Complement
	@@dna = {
		"G" => "C",
		"C" => "G",
		"T" => "A",
		"A" => "U"
	}
	@@rna = @@dna.invert

	def self.match(str, map)
		regex = Regexp.union(*map.collect { |k,v| k })
		str.gsub(regex) { |m| map[m] }
	end

	def self.error(string, type, r)
		raise ArgumentError, "Not a valid #{type} string" if string.match /#{r}/
	end

	def self.of_dna(str)
		error(str, "DNA","U")
		match(str, @@dna)
	end

	def self.of_rna(str)
		error(str, "RNA","T")
		match(str, @@rna)
	end
end
