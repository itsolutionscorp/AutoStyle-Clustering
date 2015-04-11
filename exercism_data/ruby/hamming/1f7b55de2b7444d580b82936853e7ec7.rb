# passes all skipped tests as well
class Hamming
  def self.compute(first, second)
    distance = 0
    common_length = [ first.size, second.size ].min - 1

    0.upto(common_length) do |i|
      distance +=1 if first[i] != second[i]
    end

    return distance
  end
end
