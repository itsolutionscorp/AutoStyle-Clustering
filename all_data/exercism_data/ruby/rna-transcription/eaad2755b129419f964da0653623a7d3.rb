class Complement
	def self.of_dna(str)
		str2 = String.new()
		str.each_char do |ch|
			case ch
			when 'C'
				str2.concat('G')
			when 'G'
				str2.concat('C')
			when 'T'
				str2.concat('A')
			when 'A'
				str2.concat('U')
			else
				"#{ch} is not G C A or U"
			end
		end
		return str2
	end

	def self.of_rna(str)
		str2 = String.new()
		str.each_char do |ch|
			case ch
			when 'C'
				str2.concat('G')
			when 'G'
				str2.concat('C')
			when 'U'
				str2.concat('A')
			when 'A'
				str2.concat('T')
			else
				"#{ch} is not G C A or U"
			end
		end
		return str2
	end

end
