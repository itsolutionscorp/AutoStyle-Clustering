class Hamming
	def self.compute(arg1,arg2)
		counter = 0
		if first_is_bigger(arg1,arg2)
			for i in 0...arg2.length
				if arg1[i]!=arg2[i]
					counter=counter+1
				end
			end
		else
			for i in 0...arg1.length
				if arg1[i]!=arg2[i]
					counter=counter+1
				end
			end
		end
		puts counter
		return counter
	end


	def self.first_is_bigger(arg1,arg2)
		if arg1.length >= arg2.length
			puts 'arg1 longer arg2'
			return true
		else
			puts 'arg2 longer arg1'
			return false
		end
	end
end

Hamming.compute('AGAGACTTA', 'AAA')
