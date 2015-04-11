class Raindrops

require 'prime'

	def self.convert(val)
		
		array = Prime.prime_division(val)
		
		raindrops = ""
		
		array.length.times do |x| 
			raindrops = "Pling" if array[x][0] == 3
			raindrops += "Plang" if array[x][0] == 5
			raindrops += "Plong" if array[x][0] == 7
		end
	
		raindrops = "#{val}" if raindrops == ""
		
		raindrops	
	
	end
	
end
