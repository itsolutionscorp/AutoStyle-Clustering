class Complement

	@dna_to_rna = { :g => "c", :c => "g", :t => "a", :a => "u"}


	def self.of_dna(string)
		new_string = ""
		string.downcase.each_char do |char|
			@dna_to_rna.each do |x,y|
				new_string << y if x.to_s == char 
			end
		end
		new_string.upcase
	end

	def self.of_rna(string)
		new_string = ""
		string.downcase.each_char do |char|
			@dna_to_rna.each do |x|
				rna_to_dna = x.reverse
				new_string << rna_to_dna[1].to_s if rna_to_dna[0] == char
			end
		end
		new_string.upcase
	end
end
