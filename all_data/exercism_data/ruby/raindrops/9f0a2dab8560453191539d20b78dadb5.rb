require 'prime'


class Raindrops
	def self.prime arg
	  return arg.prime_division
	end

	def self.convert arg
		@primes =  prime(arg)
		new_ary = []
		@primes.each do |ary|
			new_ary << ary.first
		end

		message = ""

		case 
		when new_ary.include?(3)

			message << "Pling"
			if new_ary.include?(5)
				message << "Plang"
				if new_ary.include?(7)
					message	<< "Plong"
				end
			elsif new_ary.include?(7)
				message << "Plong"
			end
			return message

		when new_ary.include?(5)

			message << "Plang"
			if new_ary.include?(7)
				message << "Plong"
			end
			return message

		when new_ary.include?(7)

			message << "Plong"
			return message

		else 
			return arg.to_s
			
		end
	end
end		
