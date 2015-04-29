require 'Pry'

class Array

	def keep
		result = Array.new
		each do |e|
			if yield(e)
				result << e
			end
		end
		result
	end

	def discard
		result = Array.new
		each do |e|
			if yield(e) == false
				result << e
			end
		end
		result
	end

end
