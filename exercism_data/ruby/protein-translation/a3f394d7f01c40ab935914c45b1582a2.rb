class Translation
	
	PROTEINS = {
		"AUG"             => "Methionine",
		"UUU|UUC"         => "Phenylalanine",
		"UUA|UUG"         => "Leucine",
		"UCU|UCC|UCA|UCG" => "Serine",
		"UAU|UAC"         => "Tyrosine",
		"UGU|UGC"         => "Cystine",
		"UGG"             => "Tryptophan",
		"UAA|UAG|UGA"     => "STOP"
	}

	PROTEINS.default_proc = lambda do |hash, lookup|
		hash.each_pair do |key, value|
			return value if key =~ lookup
		end
		nil
	end
		
	def self.of_codon(codon)
		protein = PROTEINS[/#{codon}/]
		raise InvalidCodonError unless protein
		protein
	end

	def self.of_rna(strand)
		strand.chars.each_slice(3).with_object([]) do |chars, pptide|
			protein = of_codon(chars.join(''))
			return pptide if protein == "STOP"
			pptide << protein
		end
	end

end

class InvalidCodonError < StandardError; end
