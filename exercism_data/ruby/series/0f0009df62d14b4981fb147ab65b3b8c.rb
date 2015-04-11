class Series
  def initialize(digits)
    @digits = digits.each_char.map(&:to_i)
  end

  def slices(length)
    raise ArgumentError if length > @digits.size
    @digits.each_cons(length).to_a
  end
end
