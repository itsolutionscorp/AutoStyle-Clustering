class SumOfMultiples
	DEFAULT_DIVISORS = [3, 5]

	def initialize *nums
		@divisors = *nums
	end

	def to num
		Foo.foo num, @divisors
	end

	def self.to num
		Foo.foo num, DEFAULT_DIVISORS
	end
end

class Foo 
	def self.foo num, divisors
	1.upto(num -1).select {|n|
			divisors.map {|d|
				n % d == 0
			}.any?
		}.inject(&:+) || 0
	end
end
