require 'prime'
class PrimeFactors

	def self.for(number)
		out = []
		prime_arr = number.prime_division()
		prime_arr.each do |sub_arr|
			sub_arr[1].times do 
				out << sub_arr[0]
			end
		end
		out
	end

end
