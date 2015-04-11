class Complement
	DNA = "GCTA"
	RNA = "CGAU"

	def self.error(str, type)
		raise ArgumentError, "Bad input" unless str.chars.all? { |x| /[#{type}]/.match(x) }
	end

	def self.of_dna(str)
		error(str, DNA) || str.tr(DNA, RNA)
	end

	def self.of_rna(str)
		error(str, RNA) || str.tr(RNA, DNA)
	end
end
