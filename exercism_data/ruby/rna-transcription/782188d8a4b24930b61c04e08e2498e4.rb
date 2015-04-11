=begin Pseudocode
	Nitpicked. Can refactor code. Use Hash instead of case statement. Use (and research) map.
=end

class Complement

	@@DNA_TO_RNA_HASH = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

	def self.of_dna(strand)
		convert(strand, @@DNA_TO_RNA_HASH)
	end

	def self.of_rna(strand)
		convert(strand, @@DNA_TO_RNA_HASH.invert)
	end

	def self.convert(strand, conversion_hash)
		return strand.split("").map{|char| conversion_hash[char]}.join("")
	end

end
