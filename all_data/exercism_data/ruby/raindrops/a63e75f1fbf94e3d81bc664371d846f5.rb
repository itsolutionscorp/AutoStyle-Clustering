class Raindrops
	def self.convert(number)
		primes = self.generate_primes(number)
		str = ""

		for i in 0...primes.length do
			if (i!=0) && (primes[i]==primes[i-1]) then
				next
			else
				case primes[i]
				when 3
					str << "Pling"
				when 5
					str << "Plang"
				when 7
					str << "Plong"
				end
			end
		end

		if str==""
			str = number.to_s
		end
		return str
	end
	def self.generate_primes(number)
		primes = Array.new
		while (number!= 1) do
			2.upto(number) do |n|
				if (number%n==0) then
					number = number/n
					primes << n
					break
				end
			end
		end
		return primes
	end
end
