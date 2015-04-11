class Array

	def accumulate
	    result = []
	    each do |var|
	      result << (yield var)
	    end
	    result
  end
  
end
