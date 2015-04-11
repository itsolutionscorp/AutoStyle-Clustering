module Raindrops

	def self.convert(number)
		drops = ''
		if number % 3 == 0
			drops << 'Pling'
		end
		if number % 5 == 0
			drops << 'Plang'
		end
		if number % 7 == 0
			drops << 'Plong'
		end
		if drops == ''
			drops << number.to_s
		end
		drops
		#make a raindrops string empty.  then in a loop, if 3, add pling, if 5 add plang, if 7 add plong.  
	end

end
