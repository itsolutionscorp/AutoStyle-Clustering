class Hamming
  def self.compute(strand1, strand2)
    if strand1.length < strand2.length
      dist_between_strands(strand1, strand2)
    else
      dist_between_strands(strand2, strand1)
    end
  end

  def self.dist_between_strands(from_strand, to_strand)
    dist = 0

    from_strand.split("").each_with_index do |char, index|
      dist += 1 if char != to_strand[index]
    end

    dist
  end
end
