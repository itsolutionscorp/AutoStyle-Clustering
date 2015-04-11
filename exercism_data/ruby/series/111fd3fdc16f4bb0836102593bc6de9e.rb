class Series
  def initialize(s)
    @string = s
  end

  def slices(n)
    raise ArgumentError if n > @string.length
    @string.split(//).map(&:to_i).each_cons(n).to_a
  end
end
