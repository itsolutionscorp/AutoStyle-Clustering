class Complement
C = Hash["G" => "c", "C" => "g", "T" => "a", "A" => "u"]

	def self.of_dna(na)
		C.each { |k, v| na.gsub!(k, v) }
		na.upcase
	end
	def self.of_rna(na)
		na.downcase!
		C.each { |k, v| na.gsub!(v, k) }
		na
	end
end
