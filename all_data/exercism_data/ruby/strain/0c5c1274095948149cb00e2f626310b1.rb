class Array
	def keep(&predicate)
		filter_for true, &predicate
	end
	
	def discard(&predicate)
		filter_for false, &predicate
	end
	
private
	def filter_for(boolean, &criteria)
		result = []
		self.each do |item|
			result << item if criteria.call(item) == boolean
		end
		result
	end
end
