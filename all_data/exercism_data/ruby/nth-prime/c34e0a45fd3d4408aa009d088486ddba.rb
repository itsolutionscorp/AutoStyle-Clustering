require 'Prime'
class Prime
	def self.nth arg
		if arg == 0
			raise ArgumentError
		else
			Prime.take(arg).last
		end
	end
end
