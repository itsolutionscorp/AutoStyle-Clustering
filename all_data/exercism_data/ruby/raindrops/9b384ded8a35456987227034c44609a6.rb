class Raindrops
	def self.convert(value)
		result = "";
		if (value % 3 == 0)
			result += "Pling"
		end
		if (value % 5 == 0)
			result += "Plang"
		end
		if (value % 7 == 0)
			result += "Plong"
		end
		if (result.empty?)
			result += value.to_s
		end
		return result
	end
end
