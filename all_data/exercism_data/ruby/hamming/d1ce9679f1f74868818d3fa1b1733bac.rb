class Hamming
  def self.compute(strand_a = '', strand_b = '')
    distance = 0
    shorter_length = compare_strand_lengths(strand_a, strand_b)

    for index in (0..(shorter_length - 1)) do
      distance += 1 if strand_a[index] != strand_b[index]
    end

    distance
  end

  private

  def self.compare_strand_lengths(strand_a, strand_b)
    length_a, length_b = strand_a.length, strand_b.length

    if length_a < length_b
      length_a
    else
      length_b
    end
  end
end
