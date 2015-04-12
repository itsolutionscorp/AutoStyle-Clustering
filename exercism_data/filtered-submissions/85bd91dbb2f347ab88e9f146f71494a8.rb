class Hamming	
  def compute(*args)
  	total = 0
    x, y = args
    x = x.scan /\w/
    y = y.scan /\w/
    x.length >= y.length ? x.pop(x.length - y.length) : y.pop(y.length - x.length)
    new_array = x.zip(y).map { |a, b| a == b }
      new_array.map {
    	|x| if x == false; total += 1
    	end
	  }
	return total
  end  
end
