# passes all skipped tests as well
class Hamming
  def self.compute(first, second)
    distance = 0

    if first.size <= second.size
      max = first.size - 1
    else
      max = second.size - 1
    end

    0.upto(max) do |i|
      distance +=1 if first[i] != second[i]
    end

    return distance
  end
end
