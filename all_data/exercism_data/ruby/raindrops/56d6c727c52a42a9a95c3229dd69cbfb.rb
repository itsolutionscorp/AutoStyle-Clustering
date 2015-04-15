class Raindrops
	def self.convert(input)
		return input.to_s if (input % 3 != 0) && (input % 5 != 0) && (input % 7 != 0) 
		answer = ""
		answer = answer + "Pling" if input % 3 == 0
		answer = answer + "Plang" if input % 5 == 0
		answer = answer + "Plong" if input % 7 == 0
		return answer
	end
end
