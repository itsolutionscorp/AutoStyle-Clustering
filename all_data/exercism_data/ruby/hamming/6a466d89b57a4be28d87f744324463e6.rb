class Hamming

	def initialize
	end

	def self.compute(a, b)
		@a = a.split(//)	
		@b = b.split(//)
		
		compare(@a, @b)

	end

	def self.compare(a, b)
		iterate_num = find_length(a, b)
		pos = 0
		num = 0
		iterate_num.times do
			if "#{a[pos]}" == "#{b[pos]}"
				num += 0
			else
				num += 1
			end
			pos += 1
		end
		return num
	end

	def self.find_length(a, b)
		if a.length > b.length
			return b.length
		else
			return a.length
		end
	end
end
