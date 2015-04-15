class Series

  attr_reader :digits

  def initialize digits
    @digits = digits.chars.map &:to_i
  end

  def slices size
    raise ArgumentError if size > digits.size

    digits.each_cons( size ).to_a
  end

end
