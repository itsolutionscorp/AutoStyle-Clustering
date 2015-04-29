class Grains
  SQAURE_RANGE = (1..64)

  def square(square)
    raise ArgumentError "square must be an integer in the range 1..64" unless SQAURE_RANGE.cover?(square)
    2**(square-1)
  end

  def total
    SQAURE_RANGE.inject { |sum, n| sum += square(n) }
  end
end
