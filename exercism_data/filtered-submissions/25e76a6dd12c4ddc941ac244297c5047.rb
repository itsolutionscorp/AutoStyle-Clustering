class Hamming
  def compute(x, y)
    count = 0
    smallerLength = 0
    if x.length <= y.length
      smallerLength = x.length
    else
      smallerLength = y.length
    end
    (0..(smallerLength-1)).each do |i|
      if x[i] != y[i]
        count += 1
      end
    end
    return count
  end
end
