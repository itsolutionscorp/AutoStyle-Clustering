require 'prime'
class Prime
	def self.nth(number)
		raise ArgumentError if number == 0
		count = 0
		no = 1
		until count == number do
			no += 1
			count += 1 if no.prime? 
		end
		no
	end
end
