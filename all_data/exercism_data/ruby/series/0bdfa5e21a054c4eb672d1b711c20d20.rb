class Series
  def initialize(values)
    @values = values
  end

  def slices(n)
    raise ArgumentError if n > @values.length
    @values.chars.map(&:to_i).each_cons(n).to_a
  end
end
