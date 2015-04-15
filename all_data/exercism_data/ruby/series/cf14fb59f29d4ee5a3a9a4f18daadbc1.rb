class Series
  attr_accessor :series
  def initialize(series)
    @series = series.chars.map(&:to_i)
  end

  def slices(num)
    if num > series.length
      raise ArgumentError
    end
    series.each_cons(num).to_a
  end
end
