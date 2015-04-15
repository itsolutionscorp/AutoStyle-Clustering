class Prime 
	def self.nth(number)
		if number <= 0
			raise ArgumentError
		else
			array =*(2..number*100)
			i = 0
			while array.last > array[i]
				element = array[i]
				primes = array.delete_if { |x| x%element == 0 && x != element }
				i +=1
			end
			primes.at(number - 1)
		end
	end
end
