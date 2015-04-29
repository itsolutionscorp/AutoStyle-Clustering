class Hamming
  def self.compute(strand_a, strand_b)
    shortestlength = [strand_a.length, strand_b.length].min

    (0...shortestlength).count do |i|
      strand_a[i] != strand_b[i]
    end
  end
end
