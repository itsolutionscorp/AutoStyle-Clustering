require 'prime'

class Raindrops

	def self.hash_pup
		{3=>"Pling", 5=>"Plang", 7=>"Plong"}
	end

	def self.prime_factaz(numb)
		Prime.prime_division(numb).flatten.map { |i| i.to_s }
	end

	def self.convert(numb)
		result = prime_factaz(numb).map { |numb_str| hash_pup[numb_str.to_i] }.join

		result.empty? ? numb.to_s : result 
	end

end
