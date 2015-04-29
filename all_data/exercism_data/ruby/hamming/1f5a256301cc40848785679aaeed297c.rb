class Hamming
  # Compute the Hamming distance between two strands of DNA
  def self.compute(strand_x, strand_y)
    [strand_x.size,strand_y.size].min.times.count{ |c|
      strand_x[c] != strand_y[c]
    }
  end
end
