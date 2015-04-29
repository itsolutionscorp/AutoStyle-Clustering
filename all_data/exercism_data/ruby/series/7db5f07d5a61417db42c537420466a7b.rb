class Series
	def initialize(s)
		@number_string = s
	end

	def slices(x)
		slices = []
		numbers = @number_string.split('')
		raise ArgumentError if x > numbers.length
		while n = numbers.shift
			slice = [n.to_i]
			numbers.each do |e|
				slice << e.to_i if slice.length < x
			end
			slices << slice if slice.length == x
		end
		slices
	end
end
