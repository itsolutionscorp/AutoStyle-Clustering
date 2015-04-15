class Raindrops
	def self.convert(number)
	answer = ""
		if number % 3 == 0
			answer << "Pling"
		end
		if number % 5 == 0
			answer << "Plang"
		end
		if number % 7 == 0
			answer << "Plong"
		end
		if answer == ""
		 answer << number.to_s
		end
		answer
	end
end
