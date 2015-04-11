class Series
  def initialize(digits)
    @digits = digits.chars.map(&:to_i)
  end

  def slices(count)
    raise ArgumentError, "Not enough digits" if count > @digits.count
    @digits.each_cons(count).to_a
  end
end
