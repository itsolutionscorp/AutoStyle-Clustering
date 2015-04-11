class Raindrops
	def initialize
	end	

	def convert(number)
		factors = factorize(number)
		result = ''
		if factors.include? 3
			result<<'Pling'
		end
		if factors.include? 5
			result<<'Plang'
		end
		if factors.include? 7
			result<<'Plong'
		end
		if result.length == 0
			result<<number.to_s
		end
		result
	end
	
	def factorize(number)
  		return [] if number == 1
  		factor = (2..number).find {|x| number % x == 0} 
  		[factor] + factorize(number / factor)
	end

end
