class Series
  def initialize(series)
    @series = series
  end

  def largest_product(x)
    raise ArgumentError unless @series.length >= x
    slices(x).map{|i| i.inject(:*) }.max || 1
  end

  def slices(x)
    (digits.length - x + 1).times.map do |i|
      x.times.map{|y| digits[i + y] }
    end
  end

  def digits
    @series.each_char.map { |c| c.to_i }
  end
end
