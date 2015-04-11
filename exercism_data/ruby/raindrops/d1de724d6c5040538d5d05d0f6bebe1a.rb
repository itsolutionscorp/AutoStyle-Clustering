class Raindrops
	def self.convert number
		string = ""
		if number % 3 == 0
			number = number/3
			string += "Pling"
			if number % 5 == 0
				string += "Plang"
				number = number/5
				if number % 7 == 0
					string += "Plong"
				end
			elsif number % 7 == 0
				string += "Plong"
			end
		elsif number % 5 == 0
			string += "Plang"
			number = number/5
			if number % 7 == 0
				string += "Plong"
			end
		elsif number % 7 == 0
			string += "Plong"
		else
			string = number.to_s
		end
		puts string
		string
	end
end
