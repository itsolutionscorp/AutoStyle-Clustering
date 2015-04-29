class Complement

	@strend = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

	def self.of_dna(dna)	
		dna_array = dna.chars.to_a
		new_comp = ""
		dna_array.each do |value|
			if @strend[value].nil?
				dn = value
			else
				dn = @strend[value]
			end
				new_comp << dn
		end
		new_comp
	end

	def self.of_rna(dna)
		dna_array = dna.chars.to_a
		new_comp = ""
		dna_array.each do |value|
			if @strend.key(value).nil?
				dn = value
			else
				dn = @strend.key(value)
			end
				new_comp << dn
		end
		new_comp
	end

end
