class Hamming

  def self.shortest_distance(first, second)
    [first.length, second.length].min
  end

  def self.compute(strand1, strand2)
    index = 0
    hamming_distance = shortest_distance(strand1, strand2)
    hamming_distance.times do 
      if strand1[index] == strand2[index]
        hamming_distance -= 1
      end
      index += 1
    end
    hamming_distance ||= 0
  end
end
