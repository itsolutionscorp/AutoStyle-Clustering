class Hamming
	def self.compute(arg1,arg2)
		if first_is_bigger(arg1,arg2)
			return compare(arg2,arg1)
		else
			return compare(arg1,arg2)
		end
	end

	def self.compare(short_arg,long_arg)
		counter = 0
		for i in 0...short_arg.length
			if short_arg[i]!=long_arg[i]
				counter=counter+1
			end
		end
		puts counter
		return counter
	end


	def self.first_is_bigger(arg1,arg2)
		if arg1.length >= arg2.length
			return true
		else
			return false
		end
	end
end
