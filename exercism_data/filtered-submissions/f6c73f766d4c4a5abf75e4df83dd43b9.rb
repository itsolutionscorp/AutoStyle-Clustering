class Hamming
  def Hamming.compute(first, second)
    distance = 0
    (0...first.length).each do |i|
      distance += 1 unless first[i] == second[i] 
    end
    return distance
  end
end
