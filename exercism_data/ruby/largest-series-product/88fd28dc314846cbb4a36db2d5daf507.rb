class Series
  attr_reader :digits

  def initialize(number)
    @digits = number.each_char.map(&:to_i)
  end

  def slices(n)
    fail ArgumentError if n > digits.count
    digits.each_cons(n).entries
  end

  def largest_product(n)
    return 1 if n.zero?
    slices(n).map { |s| s.reduce(:*) }.max
  end
end
