class Series
	
	def initialize(series)
		@series = series
	end
	
	def digits
		@series.split(//).map {|digit| digit.to_i}
	end
	
	def slices(number)
		raise ArgumentError.new('No subsets that size possible. Enter smaller number.') if number > @series.length
		new_arr = []
		unless number == 0	
			arr = self.digits
			while arr.size >= number
				new_arr << arr.take(number)
				arr = arr.drop(1)
			end
		end
		new_arr
	end

	def largest_product(number)
		arr = self.slices(number)
		largest_product = 1
		unless arr == []
			arr.each do |sub_array|
				product = 1
				sub_array.each do |digit|
					product *= digit
				end
				if product > largest_product
					largest_product = product
				end
			end
		end
		largest_product
	end

end
