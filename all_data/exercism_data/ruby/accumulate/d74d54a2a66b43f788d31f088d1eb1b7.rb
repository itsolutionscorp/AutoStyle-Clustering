class Array

  def accumulate(&block)
    return_array = [] 
    for i in self 
      return_array << yield(i)
    end
    return_array
  end

end
