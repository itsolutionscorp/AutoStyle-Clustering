class Hamming

  def compute(strand1, strand2)
    h_distance = 0
    strand1.split("").each_with_index do |c,i|
      if c != strand2[i]
        h_distance += 1
      end
    end
    return h_distance
  end

end
