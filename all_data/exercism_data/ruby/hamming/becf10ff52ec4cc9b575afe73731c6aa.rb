class Hamming
  def self.compute(s1, s2)
    new(s1, s2).compute
  end

  def initialize(s1, s2)
    @s1 = s1
    @s2 = s2
  end

  def compute
    base_pairs.count { |b1, b2| b1 != b2 }
  end

  private

  def base_pairs
    base_pairs = @s1.chars.zip(@s2.chars)
    base_pairs.keep_if { |pair| pair.first && pair.last } 
  end
end
