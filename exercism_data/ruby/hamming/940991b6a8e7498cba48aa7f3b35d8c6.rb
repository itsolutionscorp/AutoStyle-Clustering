class Hamming
	def self.compute(arg1,arg2)
	  if first_is_longer(arg1,arg2)
			return compare(arg2,arg1)
		else
			return compare(arg1,arg2)
		end
	end

	def self.compare(short_arg,long_arg)
		counter = 0
		short_arg.length.times do |i|
			if short_arg[i]!=long_arg[i]
				counter+=1
			end
		end
		#puts counter
		return counter
	end


	def self.first_is_longer(arg1,arg2)
		if arg1.length >= arg2.length
			return true
		else
			return false
		end
	end
end
