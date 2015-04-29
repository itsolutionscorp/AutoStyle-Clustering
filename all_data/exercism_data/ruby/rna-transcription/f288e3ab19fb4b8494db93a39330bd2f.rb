class Complement
	MAP = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
	def self.of_dna(dna)
		dna.chars.map{|c| MAP[c]}.join
	end

	def self.of_rna(rna)
		_map = MAP.invert
		rna.chars.map{|c| _map[c]}.join
	end
end
