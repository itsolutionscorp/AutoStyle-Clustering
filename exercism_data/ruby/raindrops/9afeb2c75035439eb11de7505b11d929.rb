module Raindrops
	def self.convert(num)
		converts = {}
		converts[3] = "Pling"
		converts[5] = "Plang"
		converts[7] = "Plong"
		result = ""
		factors = Raindrops.primeFactors(num)
		factors.each do |factor|
			if !converts[factor].nil?
				result.concat(converts[factor])
			end
		end
		result=num.to_s if result==""
		return result
	end
	def self.primeFactors(num)
		factors = Array.new
		[3,5,7].each do |prime|
			if num%prime == 0
				factors.push(prime)
			end
		end
		return factors
	end
end
