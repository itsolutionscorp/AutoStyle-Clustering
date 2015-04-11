class Series
  attr_reader :digits

  def initialize(text)
    @digits = text.chars.map(&:to_i)
  end

  def slices(n)
    fail ArgumentError if n > @digits.length
    return [[]] if n == 0

    @digits.each_cons(n).to_a
  end

  def largest_product(n)
    slices(n).map { |arr| arr.inject(1, :*) }.max
  end
end
