class Hamming
  def self.compute(strand1, strand2)
    new(strand1, strand2).difference
  end

  def initialize(strand1, strand2)
    @strand2 = strand2.chars
    @strand1 = strand1.chars.slice(0, @strand2.length)
  end

  def difference
    @strand1.zip(@strand2).count do |nucleotides|
      nucleotides.first != nucleotides.last
    end
  end
end
