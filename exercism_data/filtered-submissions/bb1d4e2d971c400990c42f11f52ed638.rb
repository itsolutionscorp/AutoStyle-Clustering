class Hamming

  def compute(strand1, strand2)
    joined_strand = if strand1.length < strand2.length
      strand1.chars.zip(strand2.chars)
    else
      strand2.chars.zip(strand1.chars)
    end

    distance = 0

    joined_strand.each_with_index do |n|
      distance += 1 if n[0] != n[1]
    end
    return distance
  end
end
