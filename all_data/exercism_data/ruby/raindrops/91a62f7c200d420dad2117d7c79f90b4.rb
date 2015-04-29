class Raindrops
	def self.convert(number)
		response = ""

		response << "Pling" if number % 3 == 0
		response << "Plang" if number % 5 == 0
		response << "Plong" if number % 7 == 0

		response = number.to_s if response.empty?   # .empty? instead of reponse == ""
		
		response
	end
end
