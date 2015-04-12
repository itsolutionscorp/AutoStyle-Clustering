class Hamming
  def compute(first, second)
    first_array = first.split(//)
    second_array = second.split(//)
    counter_equals = 0
    for i in 0...[first_array.length, second_array.length].min
      if(first_array[i] != second_array[i])
	counter_equals +=1
      end
    end
    return counter_equals
  end
end
