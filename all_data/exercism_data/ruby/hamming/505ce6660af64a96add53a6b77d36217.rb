class Hamming
	class << self
		def compute(a, b)
			pairs = reduce_single_set(a,b)
			pairs.inject(0) do |count, pair|
				count + (pair[0] == pair[1] ? 0 : 1)
			end
		end

		def reduce_single_set(a, b)
			x, y = a.size < b.size ? [a, b[0...a.size]] : [a[0...b.size], b]
			x_array, y_array = [x.split(//), y.split(//)]
			x_array.zip(y_array)
		end
	end
end
