class Complement
	def self.of_dna(a)
		string = ''
		for i in 0..a.length
			case a[i]
				when 'G' then string[i] = 'C'
				when 'C' then string[i] = 'G'
				when 'T' then string[i] = 'A'
				when 'A' then string[i] = 'U'
			end
		end
		return string
	end

	def self.of_rna(a)
		string = ''
		for i in 0..a.length
			case a[i]
				when 'C' then string[i] = 'G'
				when 'G' then string[i] = 'C'
				when 'A' then string[i] = 'T'
				when 'U' then string[i] = 'A'
			end
		end
		return string
	end

end
