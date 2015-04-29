class Series
  attr_reader :digits

  def initialize(series)
    @digits = series.each_char.map { |c| c.to_i }
  end

  def largest_product(x)
    raise ArgumentError unless digits.length >= x
    slices(x).map{|i| i.inject(:*) }.max || 1
  end

  def slices(x)
    (digits.length - x + 1).times.map do |i|
      x.times.map{|y| digits[i + y] }
    end
  end
end
