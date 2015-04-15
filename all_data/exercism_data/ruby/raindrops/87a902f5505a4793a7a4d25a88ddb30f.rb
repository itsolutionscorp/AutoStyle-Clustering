require 'prime'
class Raindrops
	def self.convert(num)
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

		if (raindrops.empty?)
			raindrops = num.to_s
		end
		return raindrops

	end

end
