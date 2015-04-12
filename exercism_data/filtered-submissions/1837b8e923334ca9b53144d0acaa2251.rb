class Hamming
  def compute(strand1,starnd2)
    distance = 0
    index = 0

    strand1.length.times do
      break if (strand1[index] == nil) || (strand2[index] == nil)
      distance += 1 if strand1[index] != strand2[index]
      index += 1 
    end

    return distance
  end
end
