class Series
  attr_reader :digits

  def initialize(string)
    @digits = string.chars.map(&:to_i)
  end

  def largest_product(n)
    slices(n).map {|x| x.inject(1, :*) }.max
  end

  def slices(n)
    return [[]] if n == 0
    raise ArgumentError if n > digits.size
    digits.each_cons(n).to_a
  end
end
