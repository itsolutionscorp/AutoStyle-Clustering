class Raindrops
	def convert(value)
		result = ""
		value%3==0 ? result += "Pling" : ""
		value%5==0 ? result += "Plang" : ""
		value%7==0 ? result += "Plong" : ""
		result=="" ? result = value.to_s : ""
		return result
	end
end
