def compute(first, second)
		first = first.split "" 
		second = second.split "" 
		# We want the shorter strand in the first position,
		# this will cause zip to discard the longer portion of the second strand.
		first, second = second, first if second.length < first.length
		first.zip(second).reject {|e| e.first  == e.last}.length
	end	
end