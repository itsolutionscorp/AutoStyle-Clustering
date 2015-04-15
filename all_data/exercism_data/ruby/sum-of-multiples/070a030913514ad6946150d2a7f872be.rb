class SumOfMultiples
  # use 3 and 5 as the kernels by default
  def self.to(n)
    new(3, 5).to(n)
  end

  def initialize(*kernels)
    @kernels = kernels
  end

  def to(n)
    # convert the kernels to lists of multiples, remove duplicates, and then
    # sum it up
    @kernels.map{ |k| multiples(k, n) }.flatten.uniq.reduce(0, &:+)
  end

  private

  # all the multiples of k strictly less than n.
  def multiples(k, n)
    (1..last_multiplier(k, n)).map{ |i| i * k }
  end

  # the largest multiplier of k such that the multiple is strictly less than n.
  # e.g. last_multiplier(5, 100) = 19
  def last_multiplier(k, n)
    (n - 1) / k
  end
end
