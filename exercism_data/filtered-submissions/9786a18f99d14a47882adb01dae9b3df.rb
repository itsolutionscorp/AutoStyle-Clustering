class Hamming
  def compute(arg1, arg2)
    distance = 0

    if arg1.length <= arg2.length
      comparators = arg1.length
    else 
      comparators = arg2.length
    end

    for i in 0...comparators
      distance +=1 if arg1[i] != arg2[i]
    end

    return distance
  end
end
