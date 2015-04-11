class Raindrops
	def self.convert(number)
		result = String.new
		isConverted = false

		if number % 3 == 0
			result << 'Pling'
			isConverted = true
		end

		if number % 5 == 0
			result << 'Plang'
			isConverted = true
		end

		if number % 7 == 0
			result << 'Plong'
			isConverted = true
		end

		if isConverted == false
			result = number.to_s
		end

		return result
	end
end
