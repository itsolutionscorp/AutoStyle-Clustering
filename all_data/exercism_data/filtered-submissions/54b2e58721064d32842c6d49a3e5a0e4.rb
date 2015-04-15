def compute(a,b)
  	biggerList = []
  	smallerList = []


  	if a.length > b.length
  		bigger_list = a.chars
  		smaller_list = b.chars
  	else
  		bigger_list = b.chars
  		smaller_list = a.chars
  	end


    smaller_list.zip(bigger_list).count {|y, z| y != z }

 	end