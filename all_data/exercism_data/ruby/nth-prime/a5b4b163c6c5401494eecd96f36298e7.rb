class MyPrime
	def self.nth(index)
		raise ArgumentError.new("shouldn't less than 1!") if index < 1
		test_num = 1
		index.times do
			begin
				test_num += 1
			end until is_prime?(test_num)
		end
		test_num
	end

	def self.is_prime?(num)
		return true if num == 2
		2.upto(Math.sqrt(num)) do |i|
		  return false if num % i == 0
		end
		true
	end
end
