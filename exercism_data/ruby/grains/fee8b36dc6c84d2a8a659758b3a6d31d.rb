class Grains
  def square n
    fail ArgumentError if n > 64
    2**(n - 1)
  end

  def total
    (1..64).map { |n| square n }.reduce :+
  end
end
