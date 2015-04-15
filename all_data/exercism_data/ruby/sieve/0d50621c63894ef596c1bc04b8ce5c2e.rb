class Sieve
	def initialize(number)
		@number = number
	end

	def primes
		list = []
		2.upto(@number) do |num|
			list.push num
		end

		2.upto(@number) do |num|

					(num+1).upto(@number) do |number_to_be_checked_if_multiple|
						if number_to_be_checked_if_multiple%num == 0
              list.delete number_to_be_checked_if_multiple
						end
					end
				print list
			
		end
		puts list
    return list
	end
end
