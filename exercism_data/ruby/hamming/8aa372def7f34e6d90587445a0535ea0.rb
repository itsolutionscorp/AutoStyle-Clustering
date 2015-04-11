class Hamming
  def self.compute(strand_a, strand_b)
    def self.compare(a, b)
      a != b && a != nil && b != nil
    end
  strand_a.each_char.zip(strand_b.each_char).count {|pair|
    compare(pair.first, pair.last)
  }
  end
end
