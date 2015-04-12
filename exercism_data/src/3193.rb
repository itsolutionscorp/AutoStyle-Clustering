class Hamming
  def compute(first_strand, other_strand)
    return 0 if first_strand === other_strand
    return 1 if first_strand.length > other_strand.length

    distance = 0
    for i in 0...first_strand.size
      distance += 1 if first_strand[i] != other_strand[i]
    end

    distance
  end
end
