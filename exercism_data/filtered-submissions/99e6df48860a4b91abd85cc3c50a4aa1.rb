class Hamming

  def compute(strand_one, strand_two)

    total = 0

    (0...strand_one.length).each do |i|
      if strand_one[i] != strand_two[i]
        total += 1
      end
    end

    return total

  end

end
