class Hamming
  attr_reader :from, :to
  def initialize(from, to)
    @from = from
    @to = to
  end

  def self.compute(from, to)
    new(from, to).compute
  end

  def compute
    split_strands
      .collect{|n1, n2| individual_distance(n1, n2) }
      .inject(:+)
  end

  def individual_distance(n1, n2)
    n1 == n2 ? 0 : 1
  end

  def split_strands
    from.chars.zip(to.chars)
  end
end
