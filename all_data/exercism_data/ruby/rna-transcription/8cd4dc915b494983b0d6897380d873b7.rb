class Complement
	# set a hash for the replacements
	@key = {'g' => 'c', 'c' => 'g', 't' => 'a', 'a' => 'u', 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

	def self.of_dna(str1)
		# use gsub to make the replacements
		str1.gsub(/[gctaGCTA]/, @key)
	end

	def self.of_rna(str1)
		# use gsub to make the replacements
		str1.gsub(/[cgauCGAU]/, @key.invert)
	end
end
