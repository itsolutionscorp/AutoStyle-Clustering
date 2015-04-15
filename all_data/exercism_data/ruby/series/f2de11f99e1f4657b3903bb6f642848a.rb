class Series

	def initialize(num_string)
		@num = num_string.chars.map(&:to_i)
	end

	def slices(size)
		raise ArgumentError if size > @num.size
		out = Array.new
		@num.each_index do |x|
			if @num[x...x+size].size == size
				out << @num[x...x+size] 
			end
		end
		out
	end
end
