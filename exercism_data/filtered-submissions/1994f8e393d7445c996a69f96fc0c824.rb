class Hamming

  def compute(strand1, strand2)
    strand1 = strand1.split(//)
    strand2 = strand2.split(//)

    if strand1.length == strand2.length
      distance = 0

      for index in (0..strand1.length).to_a do
        strand1[index] == strand2[index] ? distance += 0 : distance += 1
      end

      return distance
    end

  end

end
