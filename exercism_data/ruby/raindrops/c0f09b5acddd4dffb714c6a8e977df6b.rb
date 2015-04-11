class Raindrops
	def self.convert(number)
		number_string=number.to_s
		if number % 3 == 0 && number % 5 != 0 &&  number % 7 != 0
			"Pling"
		elsif number % 5 == 0 && number % 3 != 0 && number % 7 != 0
			"Plang"
		elsif number % 7 == 0 && number % 3 != 0 && number % 5 != 0
			"Plong"
		elsif number % 3 == 0 && number % 5 == 0 && number % 7 != 0
			"PlingPlang"
		elsif number % 3 == 0 && number % 5 != 0 && number % 7 == 0
			"PlingPlong"
		elsif number % 5 == 0 && number % 7 == 0 && number % 3 != 0
			"PlangPlong"
		elsif number % 3 == 0 && number % 5 == 0 && number % 7 == 0
			"PlingPlangPlong"
		else
			number_string
		end
	end
end
