require 'prime'

class Raindrops
	def self.convert(f)
		if f%3 == 0

			if (f%5 == 0)
				if (f%7 == 0)
					return 'PlingPlangPlong'
				end	
			return 'PlingPlang'
			end	
			
			if (f%7 == 0)
					return 'PlingPlong'
			end	
		
		return 'Pling'	
		end	

		if f%5 == 0
				if f%7 == 0
					return 'PlangPlong'
				end 
			return 'Plang'
		end 

		if f%7 == 0
			return 'Plong'
		end 

		if ((f%3 != 0) && (f%5 != 0) && (f%7 != 0)) 
			return f.to_s
		end 

	end
end
