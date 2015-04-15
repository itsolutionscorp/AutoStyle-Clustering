class Hamming
	def self.compute(arg1,arg2)
			return first_is_longer(arg1,arg2) ? compare(arg2,arg1) : compare(arg1,arg2)
	end

	def self.compare(short_arg,long_arg)
		counter = 0
		short_arg.length.times do |i|
			counter+=1 if short_arg[i]!=long_arg[i]	
		end
		#puts counter
		return counter
	end


	def self.first_is_longer(arg1,arg2)
		arg1.length >= arg2.length
	end
end
