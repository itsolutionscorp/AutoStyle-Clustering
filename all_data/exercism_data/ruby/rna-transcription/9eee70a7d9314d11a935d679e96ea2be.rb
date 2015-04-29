class Complement
	def self.of_dna(arg)
		res = ''
		i = 0
		
		while i < arg.length

			if arg[i] == 'G'
				res[i] = 'C'
			else
				if arg[i] == 'C'
					res[i] = 'G'
				else
					if arg[i] == 'T'
						res[i] = 'A'
					else
						if arg[i] == 'A'
							res[i] =  'U'
						end
					end
				end
			end

			i = i + 1
		end

		return res
	end


	def self.of_rna(arg)
		res = ''
		i = 0
		
		while i < arg.length

			if arg[i] == 'C'
				res[i] = 'G'
			else
				if arg[i] == 'G'
					res[i] = 'C'
				else
					if arg[i] == 'U'
						res[i] = 'A'
					else
						if arg[i] == 'A'
							res[i] =  'T'
						end
					end
				end
			end

			i = i + 1
		end

		return res
	end
end
