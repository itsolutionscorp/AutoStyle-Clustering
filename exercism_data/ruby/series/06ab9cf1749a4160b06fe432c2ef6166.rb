class Series
  def initialize(series)
    @series = series.chars.map(&:to_i)
    @count = @series.length
  end

  def slices(length)
    fail ArgumentError if length > @count

    valid_range = 0..(@count - length)
    valid_range.map do |index|
      @series[index..-1].first length
    end
  end
end
