class Series
  def initialize string
    @digits = string.split("").map(&:to_i)
    @result = Array.new
  end

  def slices number
    raise ArgumentError if number > @series.number
    while @digits.count >= number
      @result << @digits.take(number)
      @digits.shift
    end
    @result
  end
end
