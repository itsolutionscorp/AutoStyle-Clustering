class Hamming

  def self.compute(a, b)
    counter = 0

    aa = a.split(//)
    bb = b.split(//)

    aa.each_with_index { |val, index|
      if bb[index] == nil
        return counter
      end
      if val != bb[index]
        counter += 1
      end
    }

    return counter

  end

end
