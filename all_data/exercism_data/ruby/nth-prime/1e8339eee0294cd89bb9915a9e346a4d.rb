require 'mathn'

class Prime

	def self.nth(number)
		handle_zero if number.eql? 0
		self.first(number).last
	end

	private
	
	def handle_zero
		raise ArgumentError, 'All your base are belong to us' 
	end

end
