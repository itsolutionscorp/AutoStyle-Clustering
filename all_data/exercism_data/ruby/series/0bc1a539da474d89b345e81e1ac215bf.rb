class Series
  def initialize(sample)
    @sample = sample
  end

  def slices(series_length)
    raise ArgumentError if series_length > @sample.length
    (0..@sample.length-series_length).reduce([]) do |result, i|
      result << @sample.slice(i, series_length).chars.map(&:to_i)
    end
  end
end
