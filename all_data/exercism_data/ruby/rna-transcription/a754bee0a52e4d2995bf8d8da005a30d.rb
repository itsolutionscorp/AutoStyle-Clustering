class Complement
	Map = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
	def self.of_dna(dna)
		dna.chars.map{|c| Complement::Map[c]}.join('')
	end

	def self.of_rna(rna)
		_map = Complement::Map.invert
		rna.chars.map{|c| _map[c]}.join('')
	end
end
