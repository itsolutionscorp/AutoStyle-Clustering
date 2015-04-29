class Raindrops
	def self.convert(input)
		answer = ""
		# check factors and add appropriate Rain Sounds
		answer = answer + "Pling" if input % 3 == 0
		answer = answer + "Plang" if input % 5 == 0
		answer = answer + "Plong" if input % 7 == 0
		# if answer is empty retun the input as string, otherwise return answer
		answer.empty? ? input.to_s : answer
	end
end
