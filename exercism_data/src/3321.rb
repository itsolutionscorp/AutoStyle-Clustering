class Hamming

  def compute(s1, s2)
    distance = 0
    strand_length = [s1.length, s2.length].min - 1
    0.upto(strand_length) do |i|
      if s1[i] != s2[i]
        distance += 1
      end
    end

    distance
  end

end
