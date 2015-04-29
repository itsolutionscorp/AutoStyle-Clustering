class Complement

	def self.of_dna(strand)
		translate = Hash.new
		translate['G'] = 'C'
		translate['C'] = 'G'
		translate['T'] = 'A'
		translate['A'] = 'U'
		complement = String.new

		strand.each_char do |char|
			complement += translate[char]
		end
		return complement
	end

	def self.of_rna(strand)
		translate = Hash.new
		translate['C'] = 'G'
		translate['G'] = 'C'
		translate['A'] = 'T'
		translate['U'] = 'A'
		complement = String.new

		strand.each_char do |char|
			complement += translate[char]
		end
		return complement
	end

end
