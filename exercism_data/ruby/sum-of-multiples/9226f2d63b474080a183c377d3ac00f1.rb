class SumOfMultiples
	def self.to(number)
		limit = number - 1
		range_array =*(0..limit)
		array = range_array.delete_if do |x|
			x unless x%3 == 0 || x%5 == 0
		end
		array.inject(0) {|result, element| result + element}
	end
	
	def initialize(*args)
		@x, @y, @z = *args 
	end
	
	def to(number)
		limit = number - 1
		range_array =*(0..limit)
		array = range_array.delete_if do |i|
			case 
			when @y == nil
				i unless i % @x == 0
			when @z == nil 
				i unless i % @x == 0 || i % @y == 0
			else
				i unless i % @x == 0 || i % @y == 0 || i % @z == 0
			end
		end
		array.inject(0) {|result, element| result + element}
	end
end
