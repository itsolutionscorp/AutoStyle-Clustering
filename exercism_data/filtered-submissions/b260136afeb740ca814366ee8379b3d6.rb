class Hamming
  def compute(first, second)
    distance = 0
    minlength = [first.length, second.length].min
    for counter in 0..minlength-1
      if first[counter] != second[counter]
        distance += 1
      end
    end
    return distance
  end
end
