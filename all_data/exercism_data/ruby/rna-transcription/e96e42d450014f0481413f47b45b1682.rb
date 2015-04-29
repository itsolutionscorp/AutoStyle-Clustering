class Complement
	DNA_LUT = {"G" => "C",
 	           "C" => "G",
	           "T" => "A",
	           "A" => "U"}
	RNA_LUT = Hash[DNA_LUT.each.map{ |k, v| [v, k] }]

	def self.of_dna(strand)
		strand.to_s.each_char.map { |base|
			DNA_LUT[base] or "X"
		}.join
	end

	def self.of_rna(strand)
		strand.to_s.each_char.map { |base|
			RNA_LUT[base] or "X"
		}.join
	end
end
