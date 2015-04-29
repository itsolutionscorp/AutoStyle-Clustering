class Series
  def initialize(series)
    @series = series.split('').map {|i| i.to_i}
  end

  def slices(count)
    @series.combination(count).to_a
  end

end
