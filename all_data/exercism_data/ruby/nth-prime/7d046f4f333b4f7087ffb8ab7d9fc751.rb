require 'Prime'
class Prime
	def self.nth arg
		if arg == 0
			raise ArgumentError
		else
			array = []
			x = (Prime.take(arg)).map {|x| array << x }
			array.last
		end
	end
end
