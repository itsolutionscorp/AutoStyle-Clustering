class Hamming

  def self.shortest_length(first, second)
    [first.length, second.length].min
  end

  def self.compute(strand1, strand2)
    index = 0
    hamming_count = shortest_length(strand1, strand2)
    hamming_count.times do 
      if strand1[index] == strand2[index]
        hamming_count -= 1
      end
      index += 1
    end
    hamming_count ||= 0
  end
end
