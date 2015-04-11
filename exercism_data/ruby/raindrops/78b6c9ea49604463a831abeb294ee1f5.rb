class Raindrops 
	def self.convert(number)
		output = []
		if number % 3 == 0 && number % 5 == 0 && number % 7 == 0
			output.unshift('PlingPlangPlong')
		elsif number % 5 == 0 && number % 7 == 0
			output.unshift('PlangPlong')
		elsif number % 3 == 0 && number % 5 == 0
			output.unshift('PlingPlang')
		elsif number % 3 == 0 && number % 7 == 0
			output.unshift('PlingPlong')
		elsif number % 3 == 0 
			output.unshift('Pling')
		elsif number % 5 == 0
			output.unshift('Plang')
		elsif number % 7 == 0 
			output.unshift('Plong')
		else 
			return number.to_s
		end 
	return output.join.to_s
	end
end 
