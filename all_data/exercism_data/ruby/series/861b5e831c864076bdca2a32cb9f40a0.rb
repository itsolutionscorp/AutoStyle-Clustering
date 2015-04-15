class Series
  def initialize(digit_string)
    @digit_string = digit_string
  end

  def digits
    @digits ||= @digit_string.scan(/\d/).map(&:to_i)
  end

  def slices(n)
    raise ArgumentError, "slices exceed number of digits!" if n > digits.length

    groups = digits.length - n + 1
    groups.times.map { |i| digits.slice(i, n) }
  end
end
