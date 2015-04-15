class Series
  def initialize(series)
    @series = series.chars.map { |c| c.to_i }
  end

  def slices(n)
    raise ArgumentError if n > @series.length
    @series.each_cons(n).to_a
  end
end
