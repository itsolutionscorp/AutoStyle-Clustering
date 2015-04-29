class Complement
	# set a hash for the replacements
	@dna_key = {'g' => 'c', 'c' => 'g', 't' => 'a', 'a' => 'u', 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
	@rna_key = @dna_key.invert

	def self.of_dna(str1)
		# use gsub to make the replacements
		str1.gsub(/[gctaGCTA]/, @dna_key)
	end

	def self.of_rna(str1)
		# use gsub to make the replacements
		str1.gsub(/[cgauCGAU]/, @rna_key)
	end
end
