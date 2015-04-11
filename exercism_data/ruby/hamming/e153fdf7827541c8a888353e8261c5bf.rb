class Hamming
  def self.compute(strand_a, strand_b)
    def self.compare(a, b)
      if (a == b || a == nil || b == nil) then
        0
      else
        1
      end
    end
  strand_a.each_char.zip(strand_b.each_char).map {|pair|
    compare(pair.first, pair.last)
  }.reduce(:+)
  end
end
