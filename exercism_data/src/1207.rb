class Hamming


  def compute(x, y)
    index = 0
    sum = 0
    while   index < x.length
      if x[index] == y[index]
        different = 0
      elsif x[index] != y[index]
        different = 1
        sum = sum + different
      end
      index += 1
    end 
    sum
  end
end
