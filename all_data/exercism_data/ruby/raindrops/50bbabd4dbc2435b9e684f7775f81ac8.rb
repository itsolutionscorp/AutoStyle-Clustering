class Raindrops
	#take prime factorization of n: 
	#3:"Pling", 5:"Plang", 7:"Plong" else "#{n}"
	def self.convert(n)
		str = ""
		primes = getPrimes(n)
		if primes.include?(3) 
			str += "Pling"
		end
		if primes.include?(5)
			str += "Plang"
		end
		if primes.include?(7)
			str += "Plong"
		end
		if str == ""
			str += "#{n}"
		end
		return str
	end

	#returns the prime factorization of n as an array
	#bugged, needs to divide n by 2 as many times as possible, then 3, etc
	def self.getPrimes(n)
		factors = Array.new()
		if n > 1
			(2..n/2).each do |i|
				while(n%i == 0)
						factors.push(i)
						n = n/i
				end
			end
		end
		
		if factors.empty?
			factors.push n
		end
		return factors
	end
end
