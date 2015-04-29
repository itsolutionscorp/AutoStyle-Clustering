class Prime
	def self.nth(nth_prime)
		if nth_prime < 1 then
			raise ArgumentError
		end
		prime_ctr = 1
		x = 3
		if nth_prime == 1 then
			return 2
		else
			while true
				if x % 2 > 0 then
					#we are odd, check prime
					if isPrime?(x) then
						prime_ctr += 1
						if nth_prime == prime_ctr then
							return x
						end
					end
				end
				x += 1
			end
		end
	end

	def self.isPrime?(num)
        (2..Math.sqrt(num)).each { |i| return false if num % i == 0}
        true
    end
end
