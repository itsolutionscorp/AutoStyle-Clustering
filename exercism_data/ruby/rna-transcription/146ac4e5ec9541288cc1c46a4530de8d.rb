class Complement
	@dnahash = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
	@rnahash = {"C" => "G", "G" => "C", "A" => "T", "U" => "A"}
	def self.of_dna(dna)
		rna = ""
		for n in 0...dna.length()
			rna[n] = @dnahash[dna[n]]
		end
		rna
	end
	def self.of_rna(rna)
		dna = ""
		for n in 0...rna.length()
			dna[n] = @rnahash[rna[n]]
		end
		dna
	end
end
