class Sieve

	
	def initialize (num)
		@up_to = num
	end

	def primes
		num_range = (2..@up_to).to_a
		i = 0

		while i <= (num_range.length - 1)
			num_range = remove_multiples(num_range, num_range[i])
			i += 1
		end
		num_range

	end

	private 

	def remove_multiples (num_list, factor)
		num_list.delete_if { |a| a != factor and a % factor == 0}
		
		num_list
	end


end
