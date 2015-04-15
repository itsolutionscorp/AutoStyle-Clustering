class Grains
  def initialize
  end
  def square(n)
    (2**n)/2
  end

  def total
    (1..64).inject { |sum, n| sum + square(n) }
  end
end
