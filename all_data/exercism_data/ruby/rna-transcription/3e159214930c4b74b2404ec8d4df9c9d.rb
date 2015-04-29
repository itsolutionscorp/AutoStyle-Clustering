class Complement
	Mapping = {
		G: :C,
		C: :G,
		T: :A,
		A: :U,
	}
	Mapping_inv = Mapping.invert


	def self.of_dna dna
		dna.chars.map { |a| self::Mapping[a.to_sym] }.join
	end


	def self.of_rna rna
		rna.chars.map { |a| self::Mapping_inv[a.to_sym] }.join
	end
end
