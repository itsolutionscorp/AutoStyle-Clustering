require 'prime'

class Raindrops
	def self.convert(arg)
		x = arg.prime_division
		check = x.flatten
		@rain = []
		check.each do |a| 
			if a == 3
				@rain.push("Pling")
			elsif a ==5
				@rain.push("Plang")
			elsif a == 7
				@rain.push("Plong")
			end
		end
	
		if @rain.length >= 1
			return @rain.join
		else
			return arg.to_s
		end


	end
end
