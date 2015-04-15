class FalseClass; def to_i; 0 end end
class TrueClass; def to_i; 1 end end

class Raindrops
	# Turns out I don't actually need this, but I'm keeping 
	# it here anyways cause it works and I already wrote it!
	def self.get_all_primes (max_prime)
		primes = Array.new()
		primes << 2
		next_prime = 3
		while (next_prime <= max_prime ) do
			sqroot = Math.sqrt(next_prime).floor
			is_prime = true
			primes.each {|x|
				if (x > sqroot)
					break
				end
				if (next_prime % x == 0)
					is_prime = false
					break
				end
			}
			if is_prime 
				primes << next_prime
			end
			next_prime += 2
		end
		return primes.reverse
	end

	def self.convert(number)
		rtn_string = ""
		finished_calculating = false

		#Get number of 3s
		num_threes = get_num_factors(number, 3)
		#Get number of 5s
		num_fives = get_num_factors(number, 5)
		#Get number of 7s
		num_sevens = get_num_factors(number, 7)

		# puts "threes #{num_threes}"
		# puts "Pling"*num_threes

		#Create string
		rtn_string << ("Pling"*(num_threes > 0).to_i)
		rtn_string << ("Plang"*(num_fives > 0).to_i)
		rtn_string << ("Plong"*(num_sevens > 0).to_i)

		if rtn_string == ""
			rtn_string = number.to_s
		end

		return rtn_string
	end

	def self.get_num_factors(number, prime)
		num_factors = 0
		while (number % prime == 0)
			num_factors += 1
			number = number / prime
		end
		return num_factors
	end
end
