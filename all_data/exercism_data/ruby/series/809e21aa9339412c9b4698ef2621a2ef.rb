class Series
  def initialize(str)
    @series = str.each_char.to_a.map { |x| x.to_i }
  end

  def slices(size)
    raise ArgumentError if size > @series.size
    0.upto(@series.size-size).with_object([]) do |index, result|
      result << @series[index, size]
    end
  end

end
