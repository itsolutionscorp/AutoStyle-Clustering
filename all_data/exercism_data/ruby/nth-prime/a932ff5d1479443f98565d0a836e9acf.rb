require 'prime'

class Prime

	def self.nth(i)

		raise ArgumentError if i < 1

		return Prime.take(i).last
	end
end
