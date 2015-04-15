#!/usr/bin/env ruby

class Raindrops
	def self.convert(intinput)
		
		retstring = String.new

		#multiple of 3, 5, or 7
		if (intinput % 3 == 0 || intinput % 5 == 0 || intinput % 7 == 0)


			if intinput % 3 == 0
				retstring << "Pling"
			end
			
			if intinput % 5 == 0
				retstring << "Plang"
			end

			if intinput % 7 == 0
				retstring << "Plong"
			end
		#not a multiple of 3, 5, or 7
		else
			retstring << intinput.to_s
		end

		return retstring
	end
end
