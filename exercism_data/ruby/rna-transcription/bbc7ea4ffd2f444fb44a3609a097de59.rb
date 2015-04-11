class Complement

	def self.of_dna(string)
		string.gsub!('G', 'c')
		string.gsub!('C', 'g')
		string.gsub!('T', 'a')
		string.gsub!('A', 'u')
		string.upcase
	end

	def self.of_rna(string)
		string.gsub!('C', 'g')
		string.gsub!('G', 'c')
		string.gsub!('A', 't')
		string.gsub!('U', 'a')
		string.upcase
		
	end

end
