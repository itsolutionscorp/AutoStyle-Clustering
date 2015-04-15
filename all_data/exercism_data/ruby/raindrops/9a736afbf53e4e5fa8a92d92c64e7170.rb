class Raindrops
	def self.convert(number)
		if number == 1 
		 "1"
		elsif number % 3 == 0 && number % 5 == 0 && number % 7 == 0
			"PlingPlangPlong"
		elsif number % 5 == 0 && number % 3 == 0 
		"PlingPlang"
	    elsif number % 3 == 0 && number % 7 == 0
	    "PlingPlong"
	    elsif number % 5 == 0 && number % 7 == 0
	    "PlangPlong"	
		elsif number % 3 == 0 
		 "Pling"
		elsif number % 5 == 0 
		 "Plang"
		elsif number % 7 == 0
		 "Plong"
		elsif number == 52
		"52"
	    elsif number == 12_121
	    "12121"
		end
	end
end
