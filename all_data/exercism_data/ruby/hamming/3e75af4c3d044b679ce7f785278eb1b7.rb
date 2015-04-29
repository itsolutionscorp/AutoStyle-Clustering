class Hamming
  def self.compute (x,y)

    if x.length <= y.length
      strand_size = x.length
    else
      strand_size = y.length
    end

    hamming_distance = 0



    for i in (0..(strand_size-1))
      if x[i] != y[i]
        hamming_distance += 1
      end
    end

    return hamming_distance
  end
end
