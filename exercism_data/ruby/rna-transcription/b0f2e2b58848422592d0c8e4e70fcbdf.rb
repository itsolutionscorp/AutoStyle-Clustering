class Complement

	def self.of_dna(str1)
		# set a hash that has the keys
		key = {'g' => 'c', 'c' => 'g', 't' => 'a', 'a' => 'u', 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
		# use gsub to make the replacements
		str1.gsub(/[gctaGCTA]/, key)
	end

	def self.of_rna(str1)
		# set a hash that has the keys
		key = {"c" => "g" , "g" => "c", "a" => "t", "u" => "a", "C" => "G" , "G" => "C", "A" => "T", "U" => "A"}
		# use gsub to make the replacements
		str1.gsub(/[cgauCGAU]/, key)
	end
end
