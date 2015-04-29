class Hamming
  attr_accessor :a, :b

  def initialize(a, b)
    self.a = a.split ''
    self.b = b.split ''
  end

  def self.compute(a, b)
    new(a, b).compute
  end

  def compute
    a.zip(b).map { |a, b| b && a == b }.count(false)
  end
end
