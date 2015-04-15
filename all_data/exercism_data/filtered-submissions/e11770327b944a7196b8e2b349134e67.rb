class Hamming
  def Hamming.compute(first, second)
    distance = 0
    for i in 0...first.length 
      distance += 1 unless first[i] == second[i] 
    end
    return distance
  end
end
