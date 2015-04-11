class Series
  attr_reader :series, :length

  def initialize(series)
    @series = series.chars
    @length = series.length
  end

  def slices(num)
    fail ArgumentError if num > length

    (length - num + 1).times.reduce([]) do |result, index|
      result.push(series.slice(index, num).map(&:to_i))
    end
  end
end
