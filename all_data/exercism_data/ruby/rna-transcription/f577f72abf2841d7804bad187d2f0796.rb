class Complement

	def self.of_dna(str1)
		key = {'g' => 'c', 'c' => 'g', 't' => 'a', 'a' => 'u', 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
		str1.gsub(/[gctaGCTA]/, key)
	end

	def self.of_rna(str1)
		key = {"c" => "g" , "g" => "c", "a" => "t", "u" => "a", "C" => "G" , "G" => "C", "A" => "T", "U" => "A"}
		str1.gsub(/[cgauCGAU]/, key)
	end
end
