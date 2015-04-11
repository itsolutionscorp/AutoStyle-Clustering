class Prime
	def self.nth(num)
		if num<=0
			raise ArgumentError
		end
		i = 1
		prime = 2
		while i!=num
			prime+=1
			flag = 0
			for j in 2..Math.sqrt(prime).to_i
				if prime%j == 0
					flag = 1
					break					
				end
			end
			i+=1 if flag == 0
		end
		prime

	end
end
