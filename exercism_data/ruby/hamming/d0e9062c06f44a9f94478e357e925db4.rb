class Hamming

  def self.compute(s1, s2)
    new(s1, s2).compute
  end

  def initialize(s1, s2)
    @s1 = s1
    @s2 = s2
    normalize_length
  end

  def compute
    @s1.chars.each_with_index.inject(0) do |memo, (nucleotide, index)|
      memo += 1 if nucleotide != @s2[index]
      memo
    end
  end

  private

  def normalize_length
    @s1 = @s1[0...min_length]
    @s2 = @s2[0...min_length]
  end

  def min_length
    [@s1.length, @s2.length].min
  end
end
