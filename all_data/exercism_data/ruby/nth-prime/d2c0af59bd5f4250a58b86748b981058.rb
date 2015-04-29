class Prime
	def self.nth(x)
		raise ArgumentError if x == 0 

		m = [2]
		c = 3

		while m.size != x
			prime = true
			m.each { |x| 
				if c%x == 0 and prime != false then 
					prime = false 
				end
			}
			if prime == true then m.push(c) end
			c += 2
		end
	m[x-1]
	end
end
