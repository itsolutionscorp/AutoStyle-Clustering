require 'prime'
class Raindrops
	def self.convert(num)
		raindrops = ''
		
		num % 3 == 0 ? raindrops <<'Pling' : false
		num % 5 == 0 ? raindrops <<'Plang' : false
		num % 7 == 0 ? raindrops <<'Plong' : false
				

		raindrops.empty? ? num.to_s : raindrops
	end

	def self.convertPrimes(num)
		factors = num.prime_division
		raindrops = ''

		factors.each do |factor|
			if factor[0] == 3
				raindrops += 'Pling'
			elsif factor[0] == 5
				raindrops += 'Plang'
			elsif factor[0] == 7
				raindrops += 'Plong'
			end
		end

		raindrops.empty? ? num.to_s : raindrops
	end

end
