require 'prime'

class Prime
	def self.nth (index_of_number)
		(Prime.first index_of_number).pop or raise ArgumentError 
	end
end
