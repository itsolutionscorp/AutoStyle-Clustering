class Series
  def initialize series
    @series = series.chars.map { |i| i.to_i }.to_a
    @length = series.length
  end

  def slices n
    raise ArgumentError if n > @length
    slices = []
    (0..@length - n).each { |i| slices << @series[i..i + n - 1] }
    slices
  end
end
