class Complement
	def self.of_dna(c)
		for t in 0.. (c.length)
			case c[t]
			when 'G'
				c[t] = 'C'
			when 'C'
				c[t] = 'G'
			when 'T'
				c[t] = 'A'
			when 'A'
				c[t] = 'U'
			end

		end
		c
	end

	def self.of_rna(c)
		for t in 0.. (c.length)
			case c[t]
			when 'G'
				c[t] = 'C'
			when 'C'
				c[t] = 'G'
			when 'A'
				c[t] = 'T'
			when 'U'
				c[t] = 'A'
			end

		end
		c
	end

end
