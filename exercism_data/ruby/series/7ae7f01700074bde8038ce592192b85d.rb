class Series
  attr_accessor :series
  def initialize(series)
    @series = series
  end

  def slices(num)
    if num > series.length
      raise ArgumentError
    end
    x = 0
    slice = []
    while x < series.length do
      slice << series[x..x+num-1].split("").each { |x| x.to_i }
      x += 1
    end
    slice
  end
end
