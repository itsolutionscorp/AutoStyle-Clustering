class Series
  def initialize(str)
    @digits = str.chars.map &:to_i
  end

  def slices(length)
    fail ArgumentError if length > @digits.length

    (0..(@digits.length - length)).map { |i| @digits.slice(i,length) }
  end
end
