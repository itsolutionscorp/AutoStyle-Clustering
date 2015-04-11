class Raindrops
	#take prime factorization of n: 
	#3:"Pling", 5:"Plang", 7:"Plong" else "#{n}"
	def self.convert(n)
		str = ""
		getPrimes(n).each do |prime|
			case prime
			when 3
				str += "Pling"
			when 5
				str += "Plang"
			when 7
				str += "Plong"
			end
		end
		if str == ""
			str += "#{n}"
		end
		return str
	end

	#returns the prime factorization of n as an array
	def self.getPrimes(n)
		factors = Array.new()
		while(n > 1)
			(2..n/2).each do |i|
				if n%i == 0
					factors.push(i)
					n = n/i
				end
			end
			break
		end
		if factors.empty?
			factors.push n
		end
		return factors
	end
end
