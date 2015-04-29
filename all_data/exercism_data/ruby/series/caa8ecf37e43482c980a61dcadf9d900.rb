class Series
  def initialize(series)
    @series = series
  end

  def slices(num)
    slices = []
    if num > @series.length
      raise ArgumentError
    else
      (0..@series.length - num).each do |i|
        slices << @series[i...i + num].split("").map(&:to_i)
      end
    end

    slices
  end
end
