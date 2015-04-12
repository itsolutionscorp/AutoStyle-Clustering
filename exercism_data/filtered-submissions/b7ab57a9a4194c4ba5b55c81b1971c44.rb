class Hamming

  def compute(strand1, strand2)
    size = [strand1.size, strand2.size].min

    strand1.chars.take(size).each_with_index.count { |char, index|
      char != strand2[index]
    }
  end

end
