class Hamming

  def self.compute(a,b)
  	biggerList = []
  	smallerList = []

  	#detect which string is longer and split into arrays.
  	if a.length > b.length
  		bigger_list = a.chars
  		smaller_list = b.chars
  	else
  		bigger_list = b.chars
  		smaller_list = a.chars
  	end

  	#iterate over smaller array and check against larger array
    smaller_list.zip(bigger_list).count {|y, z| y != z }

 	end
end
