class Complement
	@replace_hash = {'G' => 'C', 'C' => 'G', 'T' => 'A',  'A' => 'U'} 
	def self.of_dna(dna)
		dna.chars.reduce("") {|x, y| x << @replace_hash[y]}
	end
	def self.of_rna(rna)
		rna.chars.reduce("") {|x, y| x << @replace_hash.key(y)}
	end
end
