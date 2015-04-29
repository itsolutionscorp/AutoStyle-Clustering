class SumOfMultiples
	def initialize(a,b,*c)
		@a = a
		@b = b
		@c = c
	end

	def self.to(x)
		sum = 0
		(1...x).each do |x|
			if x % 3 == 0 then
				sum += x
				next
			end
			if x % 5 == 0 then
				sum += x
				next
			end
		end
		sum
	end

	def to(x)
		sum = 0
		(1...x).each do |x|
			if x % @a == 0 then
				sum += x
				next
			end
			if x % @b == 0 then
				sum += x
				next
			end
			@c.each do |c|
				if x % c == 0 then
					sum += x
					break
				end
			end
		end
		sum
	end
end
