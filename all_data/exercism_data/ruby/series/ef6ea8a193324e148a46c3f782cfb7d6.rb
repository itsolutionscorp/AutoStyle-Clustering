class Series
  def initialize(series)
    @series = series.chars.map { |chr| chr.to_i }
  end

  def slices(n)
    raise ArgumentError if n > @series.length

    [].tap do |out|
      (0 .. (@series.length - n)).each { |idx| out << @series[idx, n] }
    end
  end
end
