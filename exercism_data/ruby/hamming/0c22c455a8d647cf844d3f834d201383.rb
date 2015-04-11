class Hamming
  def self.compute(strand1, strand2)
    compute_difference(strand1.chars, strand2.chars, 0)
  end

  def self.compute_difference(strand1, strand2, acc)
    if strand1.empty?
      acc
    else
      acc += 1 if strand1.shift != strand2.shift
      compute_difference(strand1, strand2, acc)
    end
  end
end
