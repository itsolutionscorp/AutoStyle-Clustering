class Series
  def initialize(digits)
    @digits = digits.each_char.map(&:to_i)
  end

  def slices(n)
    fail ArgumentError if n > @digits.count
    Array(@digits.each_cons n)
  end
end
