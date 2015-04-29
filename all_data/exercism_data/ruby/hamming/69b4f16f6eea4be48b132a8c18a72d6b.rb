class Hamming

  def self.compute(strand1, strand2)
    Hamming::Strand.hamming_distance(strand1, strand2)
  end

  class Strand

    def self.hamming_distance(strand1, strand2)
      len = [strand1.size, strand2.size].min
      (0...len).count {|i| strand1[i] != strand2[i]}
    end
  end
end
