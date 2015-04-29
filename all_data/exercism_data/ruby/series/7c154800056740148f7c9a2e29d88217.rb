class Series

  attr_reader :digits

  def initialize(string)
    @digits = string.chars.map(&:to_i)
  end

  def slices(count)
    raise ArgumentError if count > digits.length
    digits.each_cons(count).to_a
  end
end
