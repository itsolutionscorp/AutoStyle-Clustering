# series.rb
class Series
  attr_reader :series

  def initialize(series)
    @series = series.chars.map(&:to_i)
  end

  def slices(n)
    fail ArgumentError if n > series.size
    (0..(series.size - n)).each_with_object([]) do |i, slices|
      slices << series[i..(i + n - 1)]
    end
  end
end
