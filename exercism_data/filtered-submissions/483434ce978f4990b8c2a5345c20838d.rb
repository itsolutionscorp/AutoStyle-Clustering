class Hamming
  def compute(first,second)
    distance = 0
    (0..first.length-1).each {|i| distance += 1 if first[i] != second[i]}
    return distance
  end
end
