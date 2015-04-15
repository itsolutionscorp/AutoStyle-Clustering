class Raindrops
	def convert(number)
		original_number = number
		prime_factors, divisor = [], 1
		while number > 1 and divisor += 1
			prime_factors << divisor and number /= divisor while number % divisor == 0
			divisor = number - 1 if divisor > Math.sqrt(number)
		end
		prime_factors.uniq!
		response = ""
		prime_factors.each do |factor|
			response << 'Pling' if factor == 3
			response << 'Plang' if factor == 5
			response << 'Plong' if factor == 7
		end
		response.empty? ? original_number.to_s : response 
	end
end
