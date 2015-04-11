class SumOfMultiples
	def self.to(number)
		range_array =*(0...number)
		array = range_array.delete_if do |x|
			x unless x%3 == 0 || x%5 == 0
		end
		array.inject(0) {|result, element| result + element}
	end
	
	def initialize(*args)
		@var = *args 
	end
	
	def to(number)
		range_array =*(1...number)
		count = @var.length
		array = range_array.delete_if do |i|
			case 
			when count == 1
				i unless i % @var[0] == 0
			when count == 2 
				i unless i % @var[0] == 0 || i % @var[1] == 0
			when count == 3
				i unless i % @var[0] == 0 || i % @var[1] == 0 || i % @var[2] == 0
			else
				fail "Too many arguments"
			end
		end
		array.inject(0) {|result, element| result + element}
	end
end
