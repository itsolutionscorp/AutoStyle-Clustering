module Raindrops
	def self.convert(num)
		result, pling, plang, plong = ['', 'Pling', 'Plang', 'Plong']
		result << pling if pling?(num)
		result << plang if plang?(num)
		result << plong if plong?(num)
		result << num.to_s if result.empty?
		result
	end

	def self.pling?(num)
		num % 3 == 0
	end

	def self.plang?(num)
		num % 5 == 0
	end

	def self.plong?(num)
		num % 7 == 0
	end
end
