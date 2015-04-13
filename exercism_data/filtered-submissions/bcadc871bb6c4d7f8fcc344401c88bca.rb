def compute(first, second)
		first = first.split ""
		second = second.split ""


		first, second = second, first if second.length < first.length
		first.zip(second).reject {|e| e.first  == e.last}.length
	end