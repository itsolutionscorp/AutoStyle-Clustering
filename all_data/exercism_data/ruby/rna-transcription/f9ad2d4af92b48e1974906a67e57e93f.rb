class Complement
	def self.of_dna(input)
		rnaString= ""
		input.each_char { |x| rnaString += DNAtoRNA(x)}
		rnaString
	rescue
		raise ArgumentError
	end

	def self.of_rna(input)
		dnaString=""
		input.each_char {|x| dnaString += RNAtoDNA(x)}
		dnaString
	rescue
		raise ArgumentError
	end

	def self.DNAtoRNA(dna)
		conversions = {A: "U", G: "C", C: "G", T: "A" }
		conversions[dna.to_sym]
	end

	def self.RNAtoDNA(rna)
		conversions = {U: "A", C: "G", G: "C", A: "T" }
		conversions[rna.to_sym]
	end
end
