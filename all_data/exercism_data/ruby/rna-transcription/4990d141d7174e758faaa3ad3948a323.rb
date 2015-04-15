class Complement
	def self.of_dna(str)
		map = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
		new = String.new
		str.each_char do |e|
			new << map[e]
		end
		return new
	end

	def self.of_rna(str)
		map = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
		new = String.new
		str.each_char do |e|
			new << map[e]
		end
		return new
	end
end
