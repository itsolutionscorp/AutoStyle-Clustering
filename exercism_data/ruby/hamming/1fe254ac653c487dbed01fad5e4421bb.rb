class Hamming
  def initialize(a, b)
    @a, @b = a, b
  end

  def self.compute(a, b)
    new(a, b).compute
  end

  def compute
    a.zip(b).count { |a, b| b.nil? ? false : a != b }
  end

  private

  def a
    @a.chars
  end

  def b
    @b.chars
  end
end
