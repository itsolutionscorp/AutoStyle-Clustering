class Prime

	def self.nombre_facteur(n)
		count=2
		racine=Math.sqrt(n)
		i=2
		while i<=racine
			if n % i == 0
				count=count+1
			end
			i=i+1
		end
		return count
	end

	def self.premier(n)
		return self.nombre_facteur(n)==2
	end

	def self.nth(n)
		prime_list = [1]
		count = 0
		(2..105000).each do |x|
			if self.premier(x)
				prime_list.push(x)
				count += 1
				if count == n
					break
				end
			end
		end
		if n == 0
			raise ArgumentError
		else
			return prime_list[n]
		end
	end

end

# prime_list = [1, 2, 3]
# 			if num == 1
# 				return 2
# 			elsif num == 2
# 				return 3
# 			else
# 				(4..1000000).each do |x|
# 					n = Math.sqrt(x) 
# 					if x%2 != 0 && x%3 != 0
# 						prime_list.push(x)
# 					end
# 				end
# 				return prime_list[num]
# 			end
# 	end
