class Array

	def accumulate
		unless block_given?
			raise MissingBlockException.new
		else
			other = []
			self.each_with_index do |x, i|
			other[i] = yield(x) 			
			end
			other
		end		
	end
end

class MissingBlockException < Exception
end

