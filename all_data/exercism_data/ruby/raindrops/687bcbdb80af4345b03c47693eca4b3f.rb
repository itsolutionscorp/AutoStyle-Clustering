require 'prime'

class Raindrops
	def self.convert(num)
		str = ""
		bool = false
		if num % 3 == 0
			str << "Pling"
			bool = true
		end
		if num % 5 == 0
			str << "Plang"
			bool = true
		end
		if num % 7 == 0
			str << "Plong"
			bool = true
		end
		if bool
			str
		else
			num
		end
	end
end
