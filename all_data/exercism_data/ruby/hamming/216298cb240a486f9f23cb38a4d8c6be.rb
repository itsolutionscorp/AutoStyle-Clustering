class Hamming

  #attr_accessor :strand_a, :strand_b

  # def strand_a
  #   @strand_a
  # end

  # def strand_a=(string)
  #   @strand_a = string
  # end
  def self.compute(strand_a, strand_b)
    Hamming.new(strand_a, strand_b).distance
  end

  def initialize(strand_a, strand_b)
    @strand_a = strand_a
    @strand_b = strand_b
  end

  def distance
    booleans.count(false)
  end

  private

  def each_character
    @strand_a.length.times
  end

  def booleans
    each_character.map {|i| @strand_a[i] == @strand_b[i]}
  end
end
