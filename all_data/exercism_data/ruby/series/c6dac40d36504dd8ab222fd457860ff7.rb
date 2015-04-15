class Series

  def initialize(series)
    @series = series
  end

  def slices(n)
    l = @series.length
    raise ArgumentError if n > l
    (0..l-n).map { |i| @series[i,n].chars.map(&:to_i) }
  end

end
