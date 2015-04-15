require 'prime'
class Prime
	def self.nth(number)
		Prime.first(number)[-1]
	end
end
