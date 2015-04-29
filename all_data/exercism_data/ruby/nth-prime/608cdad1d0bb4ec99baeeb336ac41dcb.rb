require 'prime'
class Prime
	def self.nth(number) 
		(number <= 0) ? (raise ArgumentError) : (Prime.first number).last
	end
end
