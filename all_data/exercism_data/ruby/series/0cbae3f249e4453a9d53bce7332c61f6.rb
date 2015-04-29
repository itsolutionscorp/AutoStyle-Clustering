class Series
  def initialize(str)
    @numbers = str.chars.map(&:to_i)
  end

  def slices(n)
    raise ArgumentError if (n < 1) || (n > @numbers.length)

    @numbers.each_cons(n).to_a
  end
end
