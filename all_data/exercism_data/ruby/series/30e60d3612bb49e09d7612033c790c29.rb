class Series
  def initialize(string)
    @digits = string.chars.map(&:to_i)
  end

  def slices(size)
    raise ArgumentError if size > @digits.length
    (0..(@digits.length - size)).map{|i| @digits[i,size] }
  end
end
