require 'prime'

class Prime
	def self.nth(index)
		raise ArgumentError.new("shouldn't less than 1!") if index < 1
		first(index).last
	end
end
