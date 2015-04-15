class Prime

	def self.nth(n)
		raise ArgumentError, 'Argument is not > 1' unless n > 0

		range = 2..Float::INFINITY
		range.lazy.find_all { |i| check_prime(i) }.first(n).last
	end

	private

	def self.check_prime(number)
		upper_bound = Math.sqrt(number).floor

		if (2..upper_bound).any? { |i| number % i == 0 }
			false
		else
			true
		end
	end
end

puts Prime.nth(1000)
