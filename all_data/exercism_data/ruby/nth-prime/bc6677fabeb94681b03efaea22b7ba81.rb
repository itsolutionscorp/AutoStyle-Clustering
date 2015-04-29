require 'prime'
class Prime
	def self.nth(number) 
		(Prime.first number).last or (raise ArgumentError)
	end
end
