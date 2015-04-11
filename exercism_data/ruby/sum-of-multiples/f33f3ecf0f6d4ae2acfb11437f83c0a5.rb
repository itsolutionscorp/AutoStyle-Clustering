require 'pry'
class SumOfMultiples
	@multiples = [3,5]
	def initialize( *args )
		@multiples = []
		@multiples = args.each{ |x| @multiples << x}
	end



	def self.to( max )
		sum = 0
		(1...max).each do |num|
			sum += num if ( @multiples.any? { |multiple| num % multiple == 0} )
		end
		sum
	end

	def to( max )
		sum = 0
		(1...max).each do |num|
		sum += num	if ( @multiples.any? { |multiple| num % multiple == 0} )
		end
		sum
	end

end
