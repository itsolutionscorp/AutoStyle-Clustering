class Raindrops

	def convert (number)
		speak = ""
		raindrop = false
		if divisibleByThree?(number)
			speak = "Pling"
			raindrop = true
		end

		if divisibleByFive?(number)
			speak << "Plang"
			raindrop = true
		end

		if divisibleBySeven?(number)
			speak << "Plong"
			raindrop = true
		end

		if !raindrop
			speak = number.to_s()
		end

		return speak
	end

	def divisibleByThree? (number)
		return number % 3 == 0
	end

	def divisibleByFive? (number)
		return number % 5 == 0
	end
		
	def divisibleBySeven? (number)
		return number % 7 == 0
	end
		
	end
