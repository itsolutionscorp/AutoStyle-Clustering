class Prime
	def self.nth(n)
		if n < 1 then raise (ArgumentError.new("Number must be greater than 0")) end

		potential_prime = 1
		count = 0
		while count != n
			potential_prime += 1
			if is_prime(potential_prime) then 
				count+=1
			end
		end
		
		potential_prime
	end

	def self.is_prime(n)
		(2..n-1).each do |divisor|
			if n % divisor == 0 then return false end
		end
		true
	end
end
