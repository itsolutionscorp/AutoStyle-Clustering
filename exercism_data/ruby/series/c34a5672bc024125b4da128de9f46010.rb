class Series
  include Enumerable

  def initialize(series)
    @series = series
  end

  def slices(slice_length)
    raise ArgumentError if slice_length > @series.length
    @slices = @series.chars.collect {|s| s.to_i}.each_cons(slice_length).to_a
  end
end
