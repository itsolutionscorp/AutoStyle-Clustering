class Complement

	def self.of_dna(arg)
		dna = ''
		dna_hash = {
			"G" => "C",
			"C" => "G",
			"T" => "A",
			"A" => "U"
		}
		arg.split('').each {|x| dna << dna_hash[x]}
		dna
	end

	def self.of_rna(arg)
		rna = ''
		rna_hash = {
			"G" => "C",
			"C" => "G",
			"A" => "T",
			"U" => "A"
		}
		arg.split('').each {|x| rna << rna_hash[x]}
		rna
  end

end
