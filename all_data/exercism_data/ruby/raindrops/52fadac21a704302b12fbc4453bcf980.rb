class Raindrops
	def self.convert(input)
		answer = ""
		answer = answer + "Pling" if input % 3 == 0
		answer = answer + "Plang" if input % 5 == 0
		answer = answer + "Plong" if input % 7 == 0
		if answer == ""
			return input.to_s
		else
			answer
		end
	end
end
