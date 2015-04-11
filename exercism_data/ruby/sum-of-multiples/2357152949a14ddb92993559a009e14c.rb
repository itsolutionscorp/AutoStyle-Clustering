class SumOfMultiples
	def initialize(*mults)
		@mults = mults
	end

	def self.to(x)
		sum = 0
		(1...x).each { |x| sum += x if x % 3 == 0 || x % 5 == 0 }	
		sum
	end

	def to(x)
		sum = 0
		(1...x).each do |x|
			@mults.each do |y|
				if x % y == 0 then
					sum += x
					break
				end
			end
		end
		sum
	end
end
