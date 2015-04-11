class Hamming

  def self.compute(source, target)
    # assume equality of both strands and count differences
    distance = 0
    for i in 0..shorter_strand(source, target).length-1
      distance += 1 if source[i] != target[i]
    end

    distance
  end

  def self.shorter_strand a, b
    # returns the smaller string/strand
    a.length <= b.length ? a : b
  end
end
