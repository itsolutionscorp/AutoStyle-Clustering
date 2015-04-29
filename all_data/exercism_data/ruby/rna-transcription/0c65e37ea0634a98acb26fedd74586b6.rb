class Complement
	RNA_MAP = {"A"=>"U", "G"=>"C", "C"=>"G", "T"=>"A"}
	DNA_MAP = RNA_MAP.invert
	def self.of_dna(dna)
		Complement.of_x(dna, RNA_MAP)
	end
	
	def self.of_rna(rna)
		Complement.of_x(rna, DNA_MAP)
	end
	
	private
	
	def self.of_x(str, map)
		ret = ""
		str.each_char do |s|
			ret += map[s]
		end
		ret
	end
	
end
