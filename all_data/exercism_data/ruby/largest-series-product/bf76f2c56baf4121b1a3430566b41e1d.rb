class Series
  def initialize(s)
    @s = s
  end

  def digits
    @s.each_char.map(&:to_i)
  end

  def slices(n)
    digits.each_cons(n).to_a
  end

  def largest_product(n)
    return 1 if n == 0
    raise ArgumentError if n > @s.size
    slices(n).map { |slice| slice.reduce(:*) } .max
  end
end
