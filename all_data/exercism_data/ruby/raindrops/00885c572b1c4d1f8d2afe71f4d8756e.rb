require 'prime'

class Raindrops
	FACTORS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
	
	def self.convert number
		selected = rain_factors(number)
		return number.to_s if selected.empty?
		return selected.map{ |factor| FACTORS[factor] }.join
	end
	
private 

	def self.prime_factors(number)
		Prime.instance.prime_division(number).map { |factor| factor[0] }
	end
	
	def self.rain_factors(number)
		prime_factors(number).select { |factor| FACTORS.keys.include?(factor) }
	end

end
