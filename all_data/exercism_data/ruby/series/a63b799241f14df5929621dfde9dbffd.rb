class Series
  attr_reader :series, :size

  def initialize(series)
    @series = series
    @size = series.length  
  end

  def slices(n)
    raise ArgumentError if n > @size

    0.upto(size-n).map do |i|
      i.upto(i+n-1).map { |char| @series[char].to_i }
    end
  end

end
