require 'prime'
class Prime
	def self.nth(number)
		throw if number <= 0
		output = Prime.first(number)
		output.last
	end
end
