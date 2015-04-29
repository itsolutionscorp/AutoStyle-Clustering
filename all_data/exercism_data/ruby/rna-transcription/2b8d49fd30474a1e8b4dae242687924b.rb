class Complement
	TRANSLATION = {
		"G" => "C",
		"C" => "G",
		"T" => "A",
		"A" => "U"
	}

	def self.translate(strand)
		strand.split(//).map do |letter|
			yield(letter)
		end.join
	end

	def self.of_dna(strand)
		translate(strand){ |l| TRANSLATION.fetch(l)}
	end

	def self.of_rna(strand)
		translate(strand){ |l| TRANSLATION.key(l)}
	end
end
