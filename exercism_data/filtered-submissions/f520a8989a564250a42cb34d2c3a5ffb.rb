class Hamming

  def compute(s1, s2)
    distance = 0
    strand_1 = s1.to_s.downcase.chars
    strand_2 = s2.to_s.downcase.chars

    strand_1.each_with_index do |c, idx|
      unless c == strand_2[idx]
        distance += 1
      end
    end

    distance
  end

end
