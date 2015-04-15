class Hamming
  attr_accessor :strand_one, :strand_two

  def initialize(strand_one, strand_two)
    self.strand_one = strand_one.split('')
    self.strand_two = strand_two.split('')
  end

  def compute
    strand_one.zip(strand_two).inject(0) do |count, pair|
      count += distance(pair.first, pair.last)
    end
  end

  def self.compute(strand_one, strand_two)
    new(strand_one, strand_two).compute
  end

  private

  def distance(single_one, single_two)
    if single_one == single_two
      0
    else
      1
    end
  end
end
